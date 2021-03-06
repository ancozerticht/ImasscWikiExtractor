package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.*
import com.hatenablog.ancozerticht.imassc.domain.service.SupportSkillListCombiner
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import javax.enterprise.context.Dependent
import javax.inject.Inject

@Dependent
class SupportSkillListService @Inject constructor(
    private val repository: WikiChartRepository,
    private val combiner: SupportSkillListCombiner
) {
    fun getSupportSkillList(): SupportSkillList {
        val supportSkillGeneralQuickChart = repository.getSupportSkillGeneralChart()
        val supportSkillOtherQuickChart = repository.getSupportSkillOtherChart()

        val model = combiner.combine(supportSkillGeneralQuickChart, supportSkillOtherQuickChart)
        val supportSkillList = SupportSkillList()
        model.forEach {
            supportSkillList.addSupportSkill(SupportSkillList.SupportSkill(
                it.general.rarity,
                it.general.cardName,
                it.general.link,
                it.general.promise,
                it.general.rest,
                it.general.trouble,
                it.general.strength,
                it.other.skill,
                it.other.skillLv
            ))
        }
        return supportSkillList
    }

    fun getSupportSkillGeneralList(): SupportSkillGeneralList {
        val model = repository.getSupportSkillGeneralChart()
        val supportSkillGeneralList = SupportSkillGeneralList()
        model.forEach {
            supportSkillGeneralList.addSupportSkillGeneral(SupportSkillGeneralList.SupportSkillGeneral(
                it.rarity,
                it.cardName,
                it.link,
                it.promise,
                it.rest,
                it.trouble,
                it.strength
            ))
        }
        return supportSkillGeneralList
    }

    fun getSupportSkillOtherList(): SupportSkillOtherList {
        val model = repository.getSupportSkillOtherChart()
        val supportSkillOtherList = SupportSkillOtherList()
        model.forEach {
            supportSkillOtherList.addSupportSkillOther(SupportSkillOtherList.SupportSkillOther(
                it.rarity,
                it.cardName,
                it.skill,
                it.skillLv
            ))
        }
        return supportSkillOtherList
    }
}
