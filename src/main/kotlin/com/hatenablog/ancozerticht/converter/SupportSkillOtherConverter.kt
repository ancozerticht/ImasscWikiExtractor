package com.hatenablog.ancozerticht.converter

import com.hatenablog.ancozerticht.entity.SupportSkillOther

class SupportSkillOtherConverter: ChartElementConverter<SupportSkillOther> {
    override fun convert(head: String, body: List<String>): SupportSkillOther {
        return SupportSkillOther(body[0], body[2], body[7], body[8])
    }
}