package com.hatenablog.ancozerticht.imassc.infrastructure.shared.translator

import org.jsoup.nodes.Element

class HierarchyReconstructor(private val headerTagName: String) {
    fun reconstruct(contents: List<Element>): Map<Element, List<Element>> {
        val headerIndexes = contents
            .withIndex()
            .filter { it.value.`is`(headerTagName) }
            .map { it.index }
        val rangesOfGroup = (headerIndexes zip headerIndexes.drop(1).plus(contents.size))
            .map { (start, endExclusive) -> start until endExclusive }

        val contentsGroupedByHeader = contents
            .withIndex()
            .filter { !it.value.`is`(headerTagName) }
            .groupBy { (idx, _) ->
                rangesOfGroup.find { range -> idx in range }
            }
            .mapKeys { it.key?.start?.let { idx -> contents[idx] } }
            .mapValues { it.value.map(IndexedValue<Element>::value) }
        return contentsGroupedByHeader
            .filterKeys { it != null }
            .mapKeys { it.key!! }
    }
}
