import { SERVER_URL } from "./validation";

interface AnswerJson {
    answer: string
}

export async function askOpenAi(prompt: String) {
    if (!prompt)
        return "";

    try {
        const response = await fetch(`${SERVER_URL}${"/ask"}`, {
            method: "POST",
            mode: "cors",
            cache: "no-cache",
            credentials: "same-origin",
            headers: {
                "Content-Type": "application/json",
            },
            redirect: "follow",
            referrerPolicy: "no-referrer",
            body: JSON.stringify({
                prompt: prompt
            }),
        });
        const jsonData: AnswerJson = await response.json();
        return jsonData.answer;
    } catch (error) {
        return "";
    }
};