package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillGeneralListService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill-general")
class SupportSkillGeneralListController {
    @Inject
    private lateinit var downloader: SupportSkillGeneralListService

    @GET
    @Produces("text/csv")
    fun download(): String {
        val supportSkillQuickChart = downloader.download()
        return "レアリティ,カード名,絆,約束,おやすみ,トラブル,体力\n" +
                supportSkillQuickChart
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkillGeneral): String {
        return row.rarity + "," + row.cardName + "," + row.link + "," +
                row.promise + "," + row.rest + "," + row.trouble + "," + row.strength
    }
}
