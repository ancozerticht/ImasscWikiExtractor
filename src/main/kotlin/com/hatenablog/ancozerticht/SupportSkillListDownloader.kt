package com.hatenablog.ancozerticht

import com.hatenablog.ancozerticht.combiner.SupportSkillListCombiner
import com.hatenablog.ancozerticht.converter.SupportSkillGeneralConverter
import com.hatenablog.ancozerticht.converter.SupportSkillOtherConverter
import com.hatenablog.ancozerticht.entity.SupportSkill
import com.hatenablog.ancozerticht.generator.SupportSkillListGeneratorA
import com.hatenablog.ancozerticht.generator.SupportSkillListGeneratorB
import java.net.URI
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("support-skill")
class SupportSkillListDownloader {
    companion object {
        private const val uri =
            "https://wikiwiki.jp/shinycolors/%E3%82%B5%E3%83%9D%E3%83%BC%E3%83%88%E3%82%B9%E3%82%AD%E3%83%AB%E4%B8%80%E8%A6%A7"
    }

    @GET
    @Produces("text/csv")
    fun download(): String {
        val generatorGeneral = SupportSkillListGeneratorA(
            HtmlFetcher.fetch(URI(uri)),
            "アイドルの絆・約束リカバー・おやすみブースト・トラブルガード・体力サポート",
            SupportSkillGeneralConverter()
        )
        val supportSkillGeneralQuickChart = generatorGeneral.generate()

        val generatorOther = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "その他",
            SupportSkillOtherConverter()
        )
        val supportSkillOtherQuickChart = generatorOther.generate()

        val combiner = SupportSkillListCombiner()
        val supportSkillQuickChart = combiner.combine(supportSkillGeneralQuickChart, supportSkillOtherQuickChart)

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