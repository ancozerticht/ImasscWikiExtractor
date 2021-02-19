package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkill
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillListService
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill")
@Dependent
class SupportSkillListController {
    @Inject
    private lateinit var service: SupportSkillListService

    @GET
    @Produces("text/csv")
    fun download(): String {
        val supportSkillQuickChart = service.getSupportSkillList()
        return "レアリティ,カード名,絆,約束,おやすみ,トラブル,体力,スキル名,スキルLv\n" +
                supportSkillQuickChart
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkill): String {
        return row.general.rarity + "," + row.general.cardName + "," + row.general.link + "," +
                row.general.promise + "," + row.general.rest + "," + row.general.trouble + "," +
                row.general.strength + "," + row.other.skill + "," + row.other.skillLv
    }
}