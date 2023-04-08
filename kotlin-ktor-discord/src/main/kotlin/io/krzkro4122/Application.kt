package io.krzkro4122

import com.google.gson.Gson
import java.util.*
import io.ktor.http.*
import io.ktor.client.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import io.ktor.client.engine.cio.*
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.*
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName
import io.ktor.util.reflect.*
import org.json.JSONObject
import kotlin.reflect.typeOf

//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.server.application.*
//import kotlinx.serialization.Serializable
//import io.ktor.serialization.kotlinx.json.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.server.application.*
//import io.ktor.server.routing.*

//@Serializable
//data class DiscordMessage(val content: String)
//
//@OptIn(InternalAPI::class)
//suspend fun sendMessageToDiscord(channelId: String, token: String) {
//
//
//    while (true) {
//        val message = readlnOrNull() ?: ""
//        if (message.equals("exit", true)) {
//            httpClient.close()
//            return
//        }
//
//        try {
//            val discordMessage = DiscordMessage(message)
//            httpClient.request("https://discord.com/api/v10/channels/$channelId/messages") {
//                method = HttpMethod.Post
//                header("Authorization", "Bot $token")
//                contentType(ContentType.Application.Json)
//                setBody(discordMessage)
//            }
//        } catch (e: Exception) {
//            println("Error while sending: " + e.localizedMessage)
//            httpClient.close()
//            return
//        }
//    }
//}

@Serializable
data class DiscordRequest (
    @SerializedName("op") val op: Int,
    @SerializedName("d") val d: D
)

@Serializable
data class DiscordResponse (
    @SerializedName("op") val op: Int,
    @SerializedName("d") val d: D_R
)

@Serializable
data class D (
    @SerializedName("token") val token: String,
    @SerializedName("intents") val intents: Int,
    @SerializedName("properties") val properties: Properties
)

@Serializable
data class D_R (
    @SerializedName("properties") val properties: Properties,
    @SerializedName("content") val content: String
)

@Serializable
data class Properties (
    @SerializedName("os") val os: String,
    @SerializedName("browser") val browser: String,
    @SerializedName("device") val device: String
)


fun main() {
    val dotenv = dotenv()
    val token = dotenv["DISCORD_TOKEN"]
    val channelId = dotenv["DISCORD_CHANNEL_ID"]

    val client = HttpClient(CIO) {
        install (WebSockets) {
            pingInterval = 41_250
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
    }

    runBlocking {
        client.webSocket (method = HttpMethod.Get, host = "gateway.discord.gg", path = "?v=10") {

            val helloEvent = incoming.receive() as Frame.Text
            println(helloEvent.readText())

            sendSerialized(
                DiscordRequest (
                    op = 2,
                    d = D (
                        token = token,
                        intents = 512,
                        properties = Properties (
                            os = "linux",
                            browser = "chrome",
                            device = "chrome"
                        )
                    )
                )
            )

            while (true) {
                val incomingMessage = incoming.receive() as? Frame.Text
//                println(otherMessage?.readText())
                val incomingMessageContent = incomingMessage?.readText()
                val convertedObject = Gson().fromJson(incomingMessageContent, DiscordResponse::class.java)
                println(convertedObject.d.content)
//                val myMessage = Scanner(System.`in`).next()
//                if (myMessage != null) {
//                    send(myMessage)
//                }
            }
        }
    }
    client.close()
}
