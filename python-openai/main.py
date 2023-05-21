from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session

from src.controllers.login import handleLogin
from src.db.database import SessionLocal, engine
import src.db.schemas as schemas
import src.db.models as models

models.Base.metadata.create_all(bind=engine)

app = FastAPI()


# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@app.get("/")
async def root():
    return {"greeting": f"{'Hi mom!'}"}


@app.post("/login", response_model=schemas.Token)
async def login(credentials: schemas.Credentials, db: Session = Depends(get_db)):
    return handleLogin(credentials, db)
