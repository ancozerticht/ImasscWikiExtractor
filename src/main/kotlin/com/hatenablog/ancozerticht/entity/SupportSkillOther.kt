package com.hatenablog.ancozerticht.entity

data class SupportSkillOther (
    val rarity: String,
    val cardName: String,
    val skill: String,
    val skillLv: String
) {
    companion object {
        val defaultObject: SupportSkillOther
            get() = SupportSkillOther("", "", "", "")
    }
}
