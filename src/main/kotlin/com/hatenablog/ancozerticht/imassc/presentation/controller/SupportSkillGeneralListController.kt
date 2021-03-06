package com.hatenablog.ancozerticht.imassc.presentation.controller

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillGeneralList
import com.hatenablog.ancozerticht.imassc.application.service.SupportSkillListService
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill-general")
@Dependent
class SupportSkillGeneralListController @Inject constructor(
    private val service: SupportSkillListService
) {
    @GET
    @Produces("text/csv")
    fun download(): String {
        val supportSkillQuickChart = service.getSupportSkillGeneralList()
        return "レアリティ,カード名,絆,約束,おやすみ,トラブル,体力\n" +
                supportSkillQuickChart.supportSkillGeneralList
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkillGeneralList.SupportSkillGeneral): String {
        return row.rarity + "," + row.cardName + "," + row.link + "," +
                row.promise + "," + row.rest + "," + row.trouble + "," + row.strength
    }
}
