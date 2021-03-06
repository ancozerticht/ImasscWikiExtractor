package com.hatenablog.ancozerticht.imassc.domain.service

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkill
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillOther
import javax.enterprise.context.Dependent

@Dependent
class SupportSkillListCombiner {
    fun combine(generalList: List<SupportSkillGeneral>, otherList: List<SupportSkillOther>): List<SupportSkill> {
        return generalList.map {
            combine(it, otherList)
        }
    }

    private fun combine(general: SupportSkillGeneral, otherList: List<SupportSkillOther>): SupportSkill {
        val other = otherList.firstOrNull {
            it.cardName == general.cardName
        } ?: SupportSkillOther.defaultObject
        return SupportSkill(general, other)
    }
}
