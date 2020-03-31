package com.hatenablog.ancozerticht

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URI
import javax.ws.rs.client.ClientBuilder

class HtmlFetcher {
    companion object {
        private const val uri = "https://wikiwiki.jp/shinycolors/%E3%82%B5%E3%83%9D%E3%83%BC%E3%83%88%E3%82%B9%E3%82%AD%E3%83%AB%E4%B8%80%E8%A6%A7"
        fun fetch(): Document? {
            val client = ClientBuilder.newClient()
            val invocation = client
                .target(URI(uri))
                .request()
                .buildGet()
            val responseStr = invocation
                .invoke()
                .readEntity(String::class.java)
            return responseStr?.let {
                Jsoup.parse(it)
            }
        }
    }
}
