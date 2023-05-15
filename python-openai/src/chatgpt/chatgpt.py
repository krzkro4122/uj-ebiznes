import openai
import os

from dotenv import load_dotenv


def init_openai():
    load_dotenv()
    openai.organization = os.getenv('OPENAI_ORG_ID')
    openai.api_key = os.getenv('OPENAI_API_KEY')


def ask_openai(prompt: str, model="text-davinci-003") -> str:

    # Generate a response
    completion = openai.Completion.create(
        engine=model,
        prompt=prompt,
        max_tokens=1024,
        n=1,
        stop=None,
        temperature=0.5,
    )
    return completion.choices[0].text


def example_usage():
    init_openai()
    print(prompt := "What's the square root of 3 equal to?")
    print(ask_openai(prompt))


if __name__ == "__main__":
    example_usage()
