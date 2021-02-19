package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillOther
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class SupportSkillOtherListService {
    @Inject
    private lateinit var repository: WikiChartRepository

    fun download(): List<SupportSkillOther> {
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
