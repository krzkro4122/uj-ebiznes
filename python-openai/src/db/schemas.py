from pydantic import BaseModel
import datetime



class TokenBase(BaseModel):
    ...

class TokenCreate(TokenBase):
    token: str
    valid_until: datetime.datetime
    created_at: datetime.datetime

class Token(TokenBase):
    id: int
    owner_id: int
    token: str

    class Config:
        orm_mode = True


class UserBase(BaseModel):
    username: str

class UserCreate(UserBase):
    password: str

class User(UserBase):
    id: int
    tokens: list[Token] = []

    class Config:
        orm_mode = True


class Credentials(BaseModel):
    username: str
    password: str


class Prompt(BaseModel):
    prompt: str

class Answer(BaseModel):
    answer: str