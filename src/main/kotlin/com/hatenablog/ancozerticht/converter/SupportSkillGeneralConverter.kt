package com.hatenablog.ancozerticht.converter

import com.hatenablog.ancozerticht.entity.SupportSkillGeneral

class SupportSkillGeneralConverter: ChartElementConverter<SupportSkillGeneral> {
    override fun convert(head: String, body: List<String>): SupportSkillGeneral {
        return SupportSkillGeneral(head, body[1], body[6], body[8], body[10], body[12], body[14])
    }
}
