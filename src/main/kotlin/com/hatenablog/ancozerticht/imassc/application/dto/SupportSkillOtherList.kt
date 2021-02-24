package com.hatenablog.ancozerticht.imassc.application.dto

class SupportSkillOtherList {
    var supportSkillOtherList: MutableList<SupportSkillOtherList.SupportSkillOther> = mutableListOf()
    fun addSupportSkillOther(supportSkill: SupportSkillOtherList.SupportSkillOther) {
        supportSkillOtherList.add(supportSkill)
    }

    data class SupportSkillOther(
        val rarity: String,
        val cardName: String,
        val skill: String,
        val skillLv: String
    )
}
