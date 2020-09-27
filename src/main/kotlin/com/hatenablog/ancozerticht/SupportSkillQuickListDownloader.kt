package com.hatenablog.ancozerticht

import com.hatenablog.ancozerticht.entity.SupportSkill
import com.hatenablog.ancozerticht.translator.HierarchyReconstructor
import com.hatenablog.ancozerticht.translator.MissingCellComplementor
import org.jsoup.nodes.Element
import java.net.URI
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill")
class SupportSkillQuickListDownloader() {
    companion object {
        private const val uri =
            "https://wikiwiki.jp/shinycolors/%E3%82%B5%E3%83%9D%E3%83%BC%E3%83%88%E3%82%B9%E3%82%AD%E3%83%AB%E4%B8%80%E8%A6%A7"
    }

    @GET
    @Produces("text/csv")
    fun download(): String {
        val contents = fetch()
        val contentsGroupedByHeader = reconstruct(contents)
        val supportSkillQuickChart = getSupportSkillQuickList(contentsGroupedByHeader)
        return "レアリティ,カード名,絆,約束,おやすみ,トラブル,体力\n" +
                supportSkillQuickChart
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun fetch(): List<Element> {
        val document = HtmlFetcher.fetch(URI(uri))
        val content = document.selectFirst("#content") ?: return emptyList()
        return content.children()
    }

    private fun reconstruct(contents: List<Element>): Map<Element, List<Element>> {
        val reconstructor = HierarchyReconstructor("h2")
        return reconstructor.reconstruct(contents)
    }

    private fun getSupportSkillQuickList(contentsGroupedByHeader: Map<Element, List<Element>>): List<SupportSkill> {
        val key = contentsGroupedByHeader.keys
            .first { it.text().startsWith("サポートスキル逆引き") }
        val quickChart = contentsGroupedByHeader.getOrElse(key, ::emptyList)
            .filter { it.hasClass("accordion-container") }
            .first { it.text().startsWith("アイドルの絆・約束リカバー・おやすみブースト・トラブルガード・体力サポート") }
            .selectFirst(".accordion-content")
            .children()
        return quickChart
            .map { Pair(getChartHead(it), getChartBody(it)) }
            .flatMap { (head, body) ->
                body.map { Pair(head, it) }
            }
            .map { (head, body) ->
                SupportSkill(head, body[1], body[6], body[8], body[10], body[12], body[14])
            }
    }

    private fun getChartHead(chart: Element): String {
        val head = chart.selectFirst("h4") ?: return ""
        return head.text().trim()
    }

    private fun getChartBody(chart: Element): List<List<String>> {
        val body = chart.selectFirst("tbody") ?: return emptyList()
        val rows = body.children().filter { tr ->
            tr.children().any { it.`is`("td") }
        }
        val complementor = MissingCellComplementor()
        val rowsComplemented = complementor.complement(rows)
        return rowsComplemented.map { row ->
            row.children().map { it.text().trim() }
        }
    }

    private fun getCsvRow(row: SupportSkill): String {
        return row.rarity + "," + row.cardName + "," + row.link + "," +
                row.promise + "," + row.rest + "," + row.trouble + "," + row.strength
    }
}
