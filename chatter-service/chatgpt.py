import asyncio
import openai
import time
import os

from dotenv import load_dotenv


async def example_counter():
    now = time.time()
    print("Started counter")
    for i in range(0, 10):
        last = now
        await asyncio.sleep(0.5)
        now = time.time()
        print(f"{i}: Was asleep for {now - last}s")


def init_openai():
    load_dotenv()
    openai.organization = os.getenv('OPENAI_ORG_ID')
    openai.api_key = os.getenv('OPENAI_API_KEY')


def ask_openai(prompt: str, model="text-davinci-003") -> str:
    print(prompt)
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


async def ask_openai_async(prompt, model="text-davinci-003"):
    loop = asyncio.get_event_loop()
    return await loop.run_in_executor(None, ask_openai, prompt, model)


async def use_chatter(prompt: str):
    init_openai()
    results = asyncio.gather(ask_openai_async(prompt))
    response = await results
    return response[0]


async def example_usage():
    init_openai()
    prompt = "What's the square root of 3 equal to?"
    results = asyncio.gather(ask_openai_async(prompt))
    response = await results
    print(response[0])


if __name__ == "__main__":
    asyncio.get_event_loop().run_until_complete(example_usage())
