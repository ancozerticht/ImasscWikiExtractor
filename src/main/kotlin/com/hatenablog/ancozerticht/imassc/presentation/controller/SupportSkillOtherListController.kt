package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillOtherList
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillListService
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill-other")
@Dependent
class SupportSkillOtherListController {
    @Inject
    private lateinit var service: SupportSkillListService

    @GET
    @Produces("text/csv")
    fun download(): String {
        val supportSkillQuickChart = service.getSupportSkillOtherList()
        return "レアリティ,カード名,スキル名,スキルLv\n" +
                supportSkillQuickChart.supportSkillOtherList
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkillOtherList.SupportSkillOther): String {
        return row.rarity + "," + row.cardName + "," + row.skill + "," + row.skillLv
    }
}
