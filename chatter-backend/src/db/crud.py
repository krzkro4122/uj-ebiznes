from typing import Optional
import datetime
from dataclasses import dataclass
from sqlalchemy.orm import Session

from . import models, schemas
from src.helpers.hash_generator import hash_password

@dataclass
class TokenInfo:
    token: str
    created_at: datetime.datetime
    valid_until: datetime.datetime


def get_user_token(db: Session, user_id: int) -> Optional[models.Token]:
    return db.query(models.Token).filter(models.Token.owner_id == user_id).order_by(models.Token.valid_until.desc()).first()


def get_user(db: Session, user_id: int) -> Optional[models.User]:
    return db.query(models.User).filter(models.User.id == user_id).first()


def get_user_by_username(db: Session, username: str) -> Optional[models.User]:
    return db.query(models.User).filter(models.User.username == username).first()


def get_users(db: Session, skip: int = 0, limit: int = 100) -> list[models.User]:
    return db.query(models.User).offset(skip).limit(limit).all()


def create_user(db: Session, user: schemas.UserCreate) -> Optional[models.User]:
    password_hash, salt = hash_password(user.password)
    db_user = models.User(
        username=user.username,
        password_hash=password_hash,
        salt=salt
    )
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

def create_token(db: Session, token_info: TokenInfo, user_id: int) -> Optional[models.User]:
    db_token = models.Token(**token_info.dict(), owner_id=user_id)
    db.add(db_token)
    db.commit()
    db.refresh(db_token)
    return db_token
