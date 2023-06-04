from flask import Flask, request
from chatgpt import use_chatter

app = Flask(__name__)
app.config["DEBUG"] = True


@app.route('/', methods=['POST'])
async def ask():
    content_type = request.headers.get('Content-Type')
    if (content_type == 'application/json'):
        json = request.json
        prompt = json["prompt"]

        answer_json = {
            "answer": await use_chatter(prompt)
        }

        return answer_json
    else:
        return 'Content-Type not supported!'

app.run(host="0.0.0.0")
