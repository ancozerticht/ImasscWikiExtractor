package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillUnitMasteryList
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillListService
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill-unit-mastery")
@Dependent
class SupportSkillUnitMasteryListController @Inject constructor(
    private val service: SupportSkillListService
) {
    @GET
    @Produces("text/csv")
    fun download(): String {
        val supportSkillQuickChart = service.getSupportSkillUnitMasteryList()
        return "レアリティ,カード名,アイデア,ひらめき,上昇ステータス,最大スキルLv,最大到達Lv\n" +
                supportSkillQuickChart.supportSkillUnitMasteryList
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkillUnitMasteryList.SupportSkillUnitMastery): String {
        return row.rarity + "," + row.cardName + "," + row.idea + "," +
                row.inspiration + "," + row.increasingStatus + "," + row.skillLv + "," +
                row.cardLvReachingToSkillMax
    }
}
