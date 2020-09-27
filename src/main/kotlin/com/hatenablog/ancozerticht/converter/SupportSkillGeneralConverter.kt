package com.hatenablog.ancozerticht.converter

import com.hatenablog.ancozerticht.entity.SupportSkill

class SupportSkillGeneralConverter: ChartElementConverter<SupportSkill> {
    override fun convert(head: String, body: List<String>): SupportSkill {
        return SupportSkill(head, body[1], body[6], body[8], body[10], body[12], body[14])
    }
}
