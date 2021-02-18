package com.hatenablog.ancozerticht.imassc.infrastructure.shared.converter

interface ChartElementConverter<T> {
    fun convert(head: String, body: List<String>): T
}
