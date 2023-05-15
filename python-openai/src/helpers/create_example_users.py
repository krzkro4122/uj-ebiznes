import hash_generator
import psycopg2
from dotenv import load_dotenv
import os

load_dotenv()

conn = psycopg2.connect(
    database="ebiznes",
    host='127.0.0.1',
    user='ebiznesuser',
    password=os.getenv('DB_PASSWORD'),
    port=os.getenv('DB_PORT')
)

cursor = conn.cursor()
cursor.execute('SELECT * FROM Users')
print(cursor.fetchall())
