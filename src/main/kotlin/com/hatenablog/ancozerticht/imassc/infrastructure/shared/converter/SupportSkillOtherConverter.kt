package com.hatenablog.ancozerticht.imassc.infrastructure.shared.converter

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillOther

class SupportSkillOtherConverter: ChartElementConverter<SupportSkillOther> {
    override fun convert(head: String, body: List<String>): SupportSkillOther {
        return SupportSkillOther(body[0], body[2], body[7], body[8])
    }
}