package com.hatenablog.ancozerticht.converter

interface ChartElementConverter<T> {
    fun convert(head: String, body: List<String>): T
}
