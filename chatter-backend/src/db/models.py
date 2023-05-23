from sqlalchemy import Column, Integer, String, TIMESTAMP, ForeignKey
from sqlalchemy.orm import relationship

from src.db.database import Base


class User(Base):

    def __str__(self):
        return f"({self.id}) {self.username}"

    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String, unique=True, index=True)
    password_hash = Column(String)
    salt = Column(String)

    tokens = relationship("Token", back_populates="owner")


class Token(Base):

    def __str__(self):
        return f"Token of ID: {self.id}, valid until {self.valid_until}"

    __tablename__ = "tokens"

    id = Column(Integer, primary_key=True, index=True)
    owner_id = Column(Integer, ForeignKey("users.id"))
    token = Column(String, unique=True, index=True)
    created_at = Column(TIMESTAMP)
    valid_until = Column(TIMESTAMP)

    owner = relationship('User', back_populates="tokens")
