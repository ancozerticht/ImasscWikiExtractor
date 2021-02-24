package com.hatenablog.ancozerticht.imassc.application.dto

class SupportSkillGeneralList {
    var supportSkillGeneralList: MutableList<SupportSkillGeneralList.SupportSkillGeneral> = mutableListOf()
    fun addSupportSkillGeneral(supportSkill: SupportSkillGeneralList.SupportSkillGeneral) {
        supportSkillGeneralList.add(supportSkill)
    }

    data class SupportSkillGeneral(
        val rarity: String,
        val cardName: String,
        val link: String,
        val promise: String,
        val rest: String,
        val trouble: String,
        val strength: String
    )
}
