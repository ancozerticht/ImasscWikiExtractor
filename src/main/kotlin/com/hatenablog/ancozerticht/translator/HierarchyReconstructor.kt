package com.hatenablog.ancozerticht.translator

import org.jsoup.nodes.Element

class HierarchyReconstructor(private val headerTagName: String) {
    fun reconstruct(contents: List<Element>): Map<Element, List<Element>> {
        val headerIndexes = contents
            .withIndex()
            .filter { it.value.`is`(headerTagName) }
            .map { it.index }
        val rangesOfGroup = (headerIndexes zip headerIndexes.drop(1).plus(contents.size))
            .map { (start, endExclusive) -> start until endExclusive }

        val contentsGroupedByIdx = contents
            .withIndex()
            .filter { !it.value.`is`(headerTagName) }
            .groupBy { (idx, _) ->
                rangesOfGroup.find { range -> idx in range }?.start
            }
            .mapValues { it.value.map { indexedValue -> indexedValue.value } }
        return contentsGroupedByIdx
            .mapKeys { it.key?.let { idx -> contents[idx] } }
            .filterKeys { it != null }
            .mapKeys { it.key!! }
    }
}
