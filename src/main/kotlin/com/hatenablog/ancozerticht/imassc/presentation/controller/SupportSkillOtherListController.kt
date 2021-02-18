package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillOther
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillOtherListService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill-other")
class SupportSkillOtherListController {
    @GET
    @Produces("text/csv")
    fun download(): String {
        val downloader = SupportSkillOtherListService()
        val supportSkillQuickChart = downloader.download()
        return "レアリティ,カード名,スキル名,スキルLv\n" +
                supportSkillQuickChart
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkillOther): String {
        return row.rarity + "," + row.cardName + "," + row.skill + "," + row.skillLv
    }
}
