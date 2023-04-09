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
import io.ktor.client.request.*
import io.ktor.util.reflect.*

import io.ktor.client.statement.*
import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.lang.Exception

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
    @SerializedName("content") val content: String,
    @SerializedName("author") val author: Author
)

@Serializable
data class Author (
    @SerializedName("id") val id: Long
)

@Serializable
data class Properties (
    @SerializedName("os") val os: String,
    @SerializedName("browser") val browser: String,
    @SerializedName("device") val device: String
)

@Serializable
data class DiscordMessage(
    val content: String
)

@Serializable
data class Product (
    val name: String,
    val price: Int,
    val quantity: Int,
    val category: String
)

fun main() {

    val products = arrayListOf<Product>(
        Product("apple", 1, 100, "food"),
        Product("banana", 2, 150, "food"),
        Product("steering wheel", 100, 10, "car"),
        Product("windshield wipers", 15, 50, "car"),
        Product("Venezuela", 1000000, 1, "country"),
        Product("Chile", 10000000, 1, "country"),
        Product("baby wipes", 5, 200, "cleaning"),
        Product("chlorox", 8, 70, "cleaning"),
    )

    val categories = arrayListOf<String>(
        "food",
        "car",
        "country",
        "cleaning"
    )

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
            val botId: Long = 1094254457958694972
            while (true) {

                val incomingMessage = incoming.receive() as? Frame.Text
                val incomingMessageContent = incomingMessage?.readText()
                val convertedObject = Gson().fromJson(incomingMessageContent, DiscordResponse::class.java)
                val content = convertedObject.d.content

                try {
                    if (convertedObject.d.author.id == botId || content == null) { continue }
                    println(content)

                    var responseContent = ""

                    if (content!!.contains("product")) {
                        responseContent = "Here are the available products:\n"
                        for (product in products) {
                            responseContent += formatProduct(product)
                        }
                    } else if (content!!.contains("categor")) {
                        for (category in categories) {
                            if (content!!.contains(category)) {
                                responseContent = "Here are the available products from the ${category} category:\n"
                                for (product in products) {
                                    if (product.category.equals(category)) {
                                        responseContent += formatProduct(product)
                                    }
                                }
                                break
                            }
                            responseContent = "Here are the available categories:\n"
                            for (category in categories) {
                                responseContent += "\t$category"
                            }
                        }
                    }

                    if (responseContent != "") {
                        client.request("https://discord.com/api/v10/channels/$channelId/messages") {
                            method = HttpMethod.Post
                            header("Authorization", "Bot $token")
                            contentType(ContentType.Application.Json)
                            setBody(Gson().toJson(DiscordMessage(responseContent)))
                        }
                    }

                } catch (e: NullPointerException) {
                    // Please don't look here
                } catch (e: Exception) {
                    println("Error while sending: " + e.localizedMessage)
                    client.close()
                }
            }
        }
    }
    client.close()
}

fun formatProduct (product: Product): String {
    return "\t$${product.price},\t${product.name},\t${product.category},\t(${product.quantity} left)\n"
}