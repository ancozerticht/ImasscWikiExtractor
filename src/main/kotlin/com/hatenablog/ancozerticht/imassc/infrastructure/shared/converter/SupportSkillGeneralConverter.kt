package com.hatenablog.ancozerticht.imassc.infrastructure.shared.converter

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillGeneral

class SupportSkillGeneralConverter: ChartElementConverter<SupportSkillGeneral> {
    override fun convert(head: String, body: List<String>): SupportSkillGeneral {
        return SupportSkillGeneral(head, body[1], body[6], body[8], body[10], body[12], body[14])
    }
}
