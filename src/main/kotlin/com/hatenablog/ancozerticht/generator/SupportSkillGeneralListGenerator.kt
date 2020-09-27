package com.hatenablog.ancozerticht.generator

import com.hatenablog.ancozerticht.entity.SupportSkill
import com.hatenablog.ancozerticht.translator.HierarchyReconstructor
import com.hatenablog.ancozerticht.translator.MissingCellComplementor
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class SupportSkillGeneralListGenerator(private val document: Document) {
    fun generate(): List<SupportSkill> {
        val contents = document.selectFirst("#content")?.children() ?: return emptyList()
        val contentsGroupedByHeader = reconstruct(contents)
        return getSupportSkillQuickList(contentsGroupedByHeader)
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
}