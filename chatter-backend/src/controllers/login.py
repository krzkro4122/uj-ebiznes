import secrets
import datetime
from typing import Optional
from fastapi import HTTPException
from sqlalchemy.orm import Session

import src.db.models as models
import src.db.schemas as schemas
import src.db.crud as crud
from src.helpers.hash_generator import check_hash


response = dict[str, str]
token = str


def handleLogin(credentials: schemas.Credentials, db: Session) -> response:
    user = crud.get_user_by_username(db, credentials.username)
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    return login(user, credentials, db)


def login(user: models.User, credentials: schemas.Credentials, db: Session) -> response:
    current_token = crud.get_user_token(db, user.id)
    if current_token:
        return skip_authentication(user.id, current_token)

    generated_token = authenticate(user, credentials)
    if not generated_token:
        raise HTTPException(status_code=401, detail="Bad credentials")
    return accept_user(user.id, generated_token, db)


def authenticate(user: models.User, credentials: schemas.Credentials) -> Optional[token]:
    if check_hash(credentials.password, user.password_hash):
        return secrets.token_urlsafe(32)
    return None


def accept_user(user_id: models.User.id, generated_token: models.Token.token, db: Session) -> response:
    print(f'\tUser with ID: {user_id} has logged in successfully! Token: {generated_token}')
    new_token = schemas.TokenCreate(
        token=generated_token,
        created_at=datetime.datetime.now(),
        valid_until=datetime.datetime.now() - datetime.timedelta(seconds=100)
    )
    db_token = crud.create_token(db, new_token, user_id)
    return db_token


def skip_authentication(user_id: models.User.id, current_token: models.Token) -> response:
    print(f'\tUser with ID: {user_id} already has a valid token: {current_token}')
    return current_token
