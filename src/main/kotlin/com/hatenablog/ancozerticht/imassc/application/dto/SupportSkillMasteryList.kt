package com.hatenablog.ancozerticht.imassc.application.dto

class SupportSkillMasteryList {
    var supportSkillMasteryList: MutableList<SupportSkillMastery> = mutableListOf()
    fun addSupportSkillMastery(supportSkill: SupportSkillMastery) {
        supportSkillMasteryList.add(supportSkill)
    }

    data class SupportSkillMastery(
        val rarity: String,
        val cardName: String,
        val idea: String,
        val inspiration: String,
        val action: String,
        val skillLv: String,
        val cardLvReachingToSkillMax: String
    )
}
