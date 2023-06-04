from fastapi import HTTPException
import requests
import json

response = dict[str, str]
prompt = str

SERVICE_URL = "http://chatter:5000"


async def handlePrompt(prompt: prompt) -> response:
    if not prompt:
        raise HTTPException(status_code=404, detail="The question is of length 0!")

    request_body = {"prompt": prompt}

    response = requests.post(SERVICE_URL , json=request_body)
    if response.status_code == 200:
        answer_json = json.loads(response.content.decode('utf-8'))
        return {"answer": answer_json["answer"]}

    return {"error": "Something went wrong"}