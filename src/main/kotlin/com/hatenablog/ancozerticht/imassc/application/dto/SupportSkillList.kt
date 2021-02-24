package com.hatenablog.ancozerticht.imassc.application.dto

class SupportSkillList {
    var supportSkillList: MutableList<SupportSkill> = mutableListOf()
    fun addSupportSkill(supportSkill: SupportSkill) {
        supportSkillList.add(supportSkill)
    }

    data class SupportSkill(
        val rarity: String,
        val cardName: String,
        val link: String,
        val promise: String,
        val rest: String,
        val trouble: String,
        val strength: String,
        val skill: String,
        val skillLv: String
    )
}
