package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillMasteryList
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillListService
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill-mastery-vi")
@Dependent
class SupportSkillMasteryViListController @Inject constructor(
    private val service: SupportSkillListService
) {
    @GET
    @Produces("text/csv")
    fun download(): String {
        val supportSkillQuickChart = service.getSupportSkillMasteryViList()
        return "レアリティ,カード名,アイデア,ひらめき,行動,最大スキルLv,最大到達Lv\n" +
                supportSkillQuickChart.supportSkillMasteryList
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkillMasteryList.SupportSkillMastery): String {
        return row.rarity + "," + row.cardName + "," + row.idea + "," +
                row.inspiration + "," + row.action + "," + row.skillLv + "," +
                row.cardLvReachingToSkillMax
    }
}
