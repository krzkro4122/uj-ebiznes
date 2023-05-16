from typing import Any, Optional
import json

from dataclasses import dataclass

from src.models.user import User
from src.helpers.singleton import SingletonMeta


@dataclass
class Credentials:
    username: str
    password: str


class Login_controller(metaclass=SingletonMeta):

    def __init__(self):
        self.is_authenticated = False

    def login(credentials: Credentials):
        login_controller = Login_controller()
        user = login_controller.authenticate(credentials)

        if not user:
            return {"error": "No such user!"}
        else:
            Login_controller.is_authenticated = True

        return {"success": f"{Login_controller.is_authenticated}"}

    def authenticate(self, credentials: Credentials) -> Optional[User]:
        ...
