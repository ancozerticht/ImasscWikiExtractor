package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkill
import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.application.dto.SupportSkillOther
import com.hatenablog.ancozerticht.imassc.domain.combiner.SupportSkillListCombiner
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class SupportSkillListService {
    @Inject
    private lateinit var repository: WikiChartRepository

    fun download(): List<SupportSkill> {
        val supportSkillGeneralQuickChart = repository.getSupportSkillGeneralChart()
        val supportSkillOtherQuickChart = repository.getSupportSkillOtherChart()

        val combiner = SupportSkillListCombiner()
        val model = combiner.combine(supportSkillGeneralQuickChart, supportSkillOtherQuickChart)
        return model.map {
            SupportSkill(
                SupportSkillGeneral(
                    it.general.rarity,
                    it.general.cardName,
                    it.general.link,
                    it.general.promise,
                    it.general.rest,
                    it.general.trouble,
                    it.general.strength
                ),
                SupportSkillOther(
                    it.other.rarity,
                    it.other.cardName,
                    it.other.skill,
                    it.other.skillLv
                )
            )
        }
    }
}