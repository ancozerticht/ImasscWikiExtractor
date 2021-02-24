package com.hatenablog.ancozerticht.imassc.infrastructure.client.converter

interface ChartElementConverter<T> {
    fun convert(head: String, body: List<String>): T
}
