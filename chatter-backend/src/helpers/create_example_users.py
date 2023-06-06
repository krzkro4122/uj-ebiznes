import os

import psycopg2
from dotenv import load_dotenv

from hash_generator import hash_password

load_dotenv()

conn = psycopg2.connect(
    database="ebiznes",
    host='localhost',
    user=os.getenv('DB_USER'),
    password=os.getenv('DB_PASSWORD'),
    port=os.getenv('DB_PORT')
)

cursor = conn.cursor()

def add_user(username: str, password: str):
    hash, salt = hash_password(password)
    query = f"INSERT INTO Users (username, password_hash, salt) VALUES ('{username}', '{hash}', '{salt}') RETURNING *"
    cursor.execute(query)
    conn.commit()
    return cursor.fetchall()

def get_users():
    cursor.execute('Select * from users')
    return cursor.fetchall()

def run():
    to_add = [
        ("Marek", "Markowicz"),
        ("Makumba", "SKA"),
        ("Dutch", "vl")
    ]
    print(f"{get_users()=}")
    for user_info in to_add:
        add_user(*user_info)
    print(f"{get_users()=}")


if __name__ == "__main__":
    run()