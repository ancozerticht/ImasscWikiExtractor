package com.hatenablog.ancozerticht.generator

import com.hatenablog.ancozerticht.converter.ChartElementConverter
import com.hatenablog.ancozerticht.translator.HierarchyReconstructor
import com.hatenablog.ancozerticht.translator.MissingCellComplementor
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class SupportSkillListGeneratorB<T>(
    private val document: Document, private val chartTitle: String,
    private val converter: ChartElementConverter<T>
) {
    fun generate(): List<T> {
        val contents = document.selectFirst("#content")?.children() ?: return emptyList()
        val contentsGroupedByHeader = reconstruct(contents)
        return getSupportSkillList(contentsGroupedByHeader)
    }

    private fun reconstruct(contents: List<Element>): Map<Element, List<Element>> {
        val reconstructor = HierarchyReconstructor("h2")
        return reconstructor.reconstruct(contents)
    }

    private fun getSupportSkillList(contentsGroupedByHeader: Map<Element, List<Element>>): List<T> {
        val chart = contentsGroupedByHeader.entries
            .firstOrNull { it.key.text().startsWith("サポートスキル逆引き") }?.value
            ?.firstOrNull { it.text().startsWith(chartTitle) }
            ?: return emptyList()
        return Pair("", getChartBody(chart))
            .let { (head, body) ->
                body.map { Pair(head, it) }
            }
            .map { (head, body) ->
                converter.convert(head, body)
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