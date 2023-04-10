# Ktor bot

A simple http client application that mimicks a discord chat bot. <br/>
Implemented with: 
- `Ktor` 
- `Kotlin` 
- `websockets` used for communicating with the discord's gateway API.
- the `cdimascio/dotenv-kotlin` package as a simple secret vault  

## Main features:
- Allows for duplex communication within a discord channel (Sending messages and receiving messages with mentions of the bot)  
- The bot can list the available products, product categories and products from a certain category:
![UJEB](https://user-images.githubusercontent.com/75375838/230934926-1dbeef16-7768-42c2-88c2-36e5e8cd09f0.png)
![IZNES](https://user-images.githubusercontent.com/75375838/230934964-b9c19686-a223-4dd1-932e-028d9698faf9.png)

## Usage remarks
To run the application, one has to create a `.env` file in the root directory of this project and populate the following variables:
- `DISCORD_CHANNEL_ID`
- `DISCORD_TOKEN`
