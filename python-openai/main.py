from fastapi import FastAPI
from dotenv import load_dotenv

import os

load_dotenv()
app = FastAPI()

OPEN_API_KEY = os.getenv('OPENAI_API_KEY')


@app.get("/")
async def root():
    return {"greeting": f"{'Hi mom!'}"}
