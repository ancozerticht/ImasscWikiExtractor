package com.hatenablog.ancozerticht

import com.hatenablog.ancozerticht.entity.SupportSkill
import com.hatenablog.ancozerticht.generator.SupportSkillGeneralListGenerator
import java.net.URI
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill")
class SupportSkillQuickListDownloader {
    companion object {
        private const val uri =
            "https://wikiwiki.jp/shinycolors/%E3%82%B5%E3%83%9D%E3%83%BC%E3%83%88%E3%82%B9%E3%82%AD%E3%83%AB%E4%B8%80%E8%A6%A7"
    }

    @GET
    @Produces("text/csv")
    fun download(): String {
        val generator = SupportSkillGeneralListGenerator(HtmlFetcher.fetch(URI(uri)))
        val supportSkillQuickChart = generator.generate()
        return "レアリティ,カード名,絆,約束,おやすみ,トラブル,体力\n" +
                supportSkillQuickChart
                    .map { getCsvRow(it) }
                    .reduce { acc, s -> acc + "\n" + s }
    }

    private fun getCsvRow(row: SupportSkill): String {
        return row.rarity + "," + row.cardName + "," + row.link + "," +
                row.promise + "," + row.rest + "," + row.trouble + "," + row.strength
    }
}
