from fastapi import FastAPI, Depends
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session

from src.controllers.login import handleLogin
from src.db.database import SessionLocal, engine
import src.db.schemas as schemas
import src.db.models as models

models.Base.metadata.create_all(bind=engine)

app = FastAPI()

origins = [
    "http://localhost:5173",
    "http://localhost",
    "localhost:5173"
    "localhost"
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"]
)


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

@app.post("/ask", response_model=schemas.Answer)
async def root(Prompt: schemas.Prompt):
    return {"answer": f"{Prompt.prompt}"}
