package com.hatenablog.ancozerticht

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URI
import javax.ws.rs.client.ClientBuilder

class HtmlFetcher {
    companion object {
        fun fetch(uri: URI): Document {
            val client = ClientBuilder.newClient()
            val target = client
                .target(uri)
            val invocation = target
                .request()
                .buildGet()
            val responseStr = invocation
                .invoke(String::class.java)
            return Jsoup.parse(responseStr)
        }
    }
}
