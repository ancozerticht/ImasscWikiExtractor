package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillOther
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import com.hatenablog.ancozerticht.imassc.infrastructure.client.SupportSkillChartPageFetcher

class SupportSkillOtherListService {
    fun download(): List<SupportSkillOther> {
        val repository: WikiChartRepository = SupportSkillChartPageFetcher()
        val model = repository.getSupportSkillOtherChart()
        return model.map {
            SupportSkillOther(
                it.rarity,
                it.cardName,
                it.skill,
                it.skillLv
            )
        }
    }
}
