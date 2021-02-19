package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class SupportSkillGeneralListService {
    @Inject
    private lateinit var repository: WikiChartRepository

    fun download(): List<SupportSkillGeneral> {
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
