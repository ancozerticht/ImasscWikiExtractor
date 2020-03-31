package com.hatenablog.ancozerticht

import com.hatenablog.ancozerticht.entity.ItemOfSupportSkillQuickList
import com.hatenablog.ancozerticht.translator.HierarchyReconstructor
import com.hatenablog.ancozerticht.translator.MissingCellComplementor
import org.jsoup.nodes.Element
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill")
class SupportSkillQuickListDownloader() {
    @GET
    @Produces("text/csv")
    fun download(): String {
        val document = HtmlFetcher.fetch() ?: return ""
        val contents = document.select("#content").first()?.children() ?: return ""

        val reconstructorWithH2 = HierarchyReconstructor("h2")
        val contentsGroupedByHeader = reconstructorWithH2.reconstruct(contents)

        val contentsOfSupportSkillQuickChart = getSupportSkillQuickList(contentsGroupedByHeader)
        return "レアリティ,カード名,絆,マスタリ,お休み,体力,トラブル,約束\n" +
                contentsOfSupportSkillQuickChart
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getSupportSkillQuickList(contentsGroupedByHeader: Map<Element, List<Element>>): List<ItemOfSupportSkillQuickList> {
        val keyOfSupportSkillQuickChart = contentsGroupedByHeader.keys
            .first { it.text().startsWith("サポートスキル早見表") }
        val supportSkillQuickChart = contentsGroupedByHeader[keyOfSupportSkillQuickChart]
            ?.filter { it.hasClass("accordion-container") }
            ?: return emptyList()
        return supportSkillQuickChart
            .map {
                Pair(
                    it.selectFirst("h3"),
                    it.selectFirst("tbody").children()
                        .filter { tr ->
                            tr.children().any { item -> item.`is`("td") }
                        }
                )
            }
            .map { (head, body) ->
                Pair(head, MissingCellComplementor().complement(body))
            }
            .map { (head, body) ->
                Pair(head.text().trim(), body.map { row -> row.children().map { it.text().trim() } })
            }
            .flatMap { (rarity, rows) ->
                rows.map { row ->
                    ItemOfSupportSkillQuickList(
                        rarity,
                        row[0],
                        row[1],
                        row[2],
                        row[3],
                        row[4],
                        row[5],
                        row[6]
                    )
                }
            }
    }

    private fun getCsvRow(row: ItemOfSupportSkillQuickList): String {
        return row.rarity + "," + row.cardName + "," + row.link + "," +
                row.mastery + "," + row.rest + "," + row.strength + "," +
                row.trouble + "," + row.promise
    }
}
