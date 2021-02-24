package com.hatenablog.ancozerticht.imassc.infrastructure.client.translator

import org.jsoup.nodes.Element

class MissingCellComplementor {
    fun complement(rows: List<Element>): List<Element> {
        if (rows.any { !it.`is`("tr") }) {
            return rows
        }

        val colNum = rows.first().childrenSize()
        var cmpledRows = rows
        for (idx in (0 until colNum)) {
            cmpledRows = complementRec(cmpledRows, idx, 0, null)
        }
        return cmpledRows
    }

    private fun complementRec(rows: List<Element>, colIdx: Int, cmplSize: Int, cmplElem: Element?): List<Element> {
        if (rows.isEmpty()) {
            return rows
        }

        val head = rows.first()
        val tail = rows.drop(1)

        if (cmplSize > 0) {
            head.insertChildren(colIdx, cmplElem?.clone())
            return listOf(head) + complementRec(tail, colIdx, cmplSize - 1, cmplElem)
        } else {
            val currentCell = head.children()[colIdx]
            if (!currentCell.hasAttr("rowspan")) {
                return listOf(head) + complementRec(tail, colIdx, 0, null)
            }

            val size = currentCell.attr("rowspan")?.toInt()?:0
            currentCell.removeAttr("rowspan")
            return listOf(head) + complementRec(tail, colIdx, size, currentCell.clone())
        }
    }
}