package com.hatenablog.ancozerticht.imassc.application.dto

class SupportSkillUnitMasteryList {
    var supportSkillUnitMasteryList: MutableList<SupportSkillUnitMastery> = mutableListOf()
    fun addSupportSkillUnitMastery(supportSkill: SupportSkillUnitMastery) {
        supportSkillUnitMasteryList.add(supportSkill)
    }

    data class SupportSkillUnitMastery(
        val rarity: String,
        val cardName: String,
        val idea: String,
        val inspiration: String,
        val increasingStatus: String,
        val skillLv: String,
        val cardLvReachingToSkillMax: String
    )
}
