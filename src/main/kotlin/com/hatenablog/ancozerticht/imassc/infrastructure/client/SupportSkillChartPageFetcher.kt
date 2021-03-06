package com.hatenablog.ancozerticht.imassc.infrastructure.client

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillMastery
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillOther
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillUnitMastery
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import com.hatenablog.ancozerticht.imassc.infrastructure.client.converter.SupportSkillGeneralConverter
import com.hatenablog.ancozerticht.imassc.infrastructure.client.converter.SupportSkillMasteryConverter
import com.hatenablog.ancozerticht.imassc.infrastructure.client.converter.SupportSkillOtherConverter
import com.hatenablog.ancozerticht.imassc.infrastructure.client.converter.SupportSkillUnitMasteryConverter
import com.hatenablog.ancozerticht.imassc.infrastructure.client.generator.SupportSkillListGeneratorA
import com.hatenablog.ancozerticht.imassc.infrastructure.client.generator.SupportSkillListGeneratorB
import java.net.URI
import javax.enterprise.context.Dependent

@Dependent
class SupportSkillChartPageFetcher: WikiChartRepository {
    companion object {
        private const val uri =
            "https://wikiwiki.jp/shinycolors/%E3%82%B5%E3%83%9D%E3%83%BC%E3%83%88%E3%82%B9%E3%82%AD%E3%83%AB%E4%B8%80%E8%A6%A7"
    }

    override fun getSupportSkillGeneralChart(): List<SupportSkillGeneral> {
        val generator = SupportSkillListGeneratorA(
            HtmlFetcher.fetch(URI(uri)),
            "アイドルの絆・約束リカバー・おやすみブースト・トラブルガード・体力サポート",
            SupportSkillGeneralConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillOtherChart(): List<SupportSkillOther> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "その他",
            SupportSkillOtherConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillMasteryVoChart(): List<SupportSkillMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "マスタリーVo系",
            SupportSkillMasteryConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillMasteryDaChart(): List<SupportSkillMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "マスタリーDa系",
            SupportSkillMasteryConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillMasteryViChart(): List<SupportSkillMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "マスタリーVi系",
            SupportSkillMasteryConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillMasteryMeChart(): List<SupportSkillMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "マスタリーMe系",
            SupportSkillMasteryConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillMasterySpChart(): List<SupportSkillMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "マスタリーSP系",
            SupportSkillMasteryConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillMasteryStaminaChart(): List<SupportSkillMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "マスタリー体力系",
            SupportSkillMasteryConverter()
        )
        return generator.generate()
    }

    override fun getSupportSkillUnitMasteryChart(): List<SupportSkillUnitMastery> {
        val generator = SupportSkillListGeneratorB(
            HtmlFetcher.fetch(URI(uri)),
            "ユニットマスタリー",
            SupportSkillUnitMasteryConverter()
        )
        return generator.generate()
    }
}
