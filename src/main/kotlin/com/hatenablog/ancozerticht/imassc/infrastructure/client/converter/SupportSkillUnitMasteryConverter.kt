package com.hatenablog.ancozerticht.imassc.infrastructure.client.converter

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillUnitMastery

class SupportSkillUnitMasteryConverter: ChartElementConverter<SupportSkillUnitMastery> {
    override fun convert(head: String, body: List<String>): SupportSkillUnitMastery {
        val regex1 = """[0-9]*(.*)""".toRegex()
        return SupportSkillUnitMastery(body[0], body[2],
            regex1.find(body[5])?.groupValues?.get(1) ?: "",
            regex1.find(body[6])?.groupValues?.get(1) ?: "",
            regex1.find(body[7])?.groupValues?.get(1) ?: "",
            body[8], body[9])
    }
}
