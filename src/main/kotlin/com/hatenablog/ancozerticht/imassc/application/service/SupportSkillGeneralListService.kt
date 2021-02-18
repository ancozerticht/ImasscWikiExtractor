package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import com.hatenablog.ancozerticht.imassc.infrastructure.client.SupportSkillChartPageFetcher

class SupportSkillGeneralListService {
    fun download(): List<SupportSkillGeneral> {
        val repository: WikiChartRepository = SupportSkillChartPageFetcher()
        val model = repository.getSupportSkillGeneralChart()
        return model.map {
            SupportSkillGeneral(
                it.rarity,
                it.cardName,
                it.link,
                it.promise,
                it.rest,
                it.trouble,
                it.strength
            )
        }
    }
}
