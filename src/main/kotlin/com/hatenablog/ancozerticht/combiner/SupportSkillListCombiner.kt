package com.hatenablog.ancozerticht.combiner

import com.hatenablog.ancozerticht.entity.SupportSkill
import com.hatenablog.ancozerticht.entity.SupportSkillGeneral
import com.hatenablog.ancozerticht.entity.SupportSkillOther

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