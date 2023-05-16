from fastapi import FastAPI

from src.controllers.login_controller import Login_controller, Credentials
from src.models.user import User

app = FastAPI()


@app.get("/")
async def root():
    return {"greeting": f"{'Hi mom!'}"}


@app.post("/login")
async def login(username: str, password: str):
    return Login_controller.login(Credentials(username, password))
