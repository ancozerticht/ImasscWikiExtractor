package com.hatenablog.ancozerticht.imassc.application.service

import com.hatenablog.ancozerticht.imassc.application.dto.*
import com.hatenablog.ancozerticht.imassc.domain.repository.WikiChartRepository
import com.hatenablog.ancozerticht.imassc.domain.service.SupportSkillListCombiner
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

    fun getSupportSkillMasteryVoList(): SupportSkillMasteryList {
        val model = repository.getSupportSkillMasteryVoChart()
        val supportSkillMasteryVoList = SupportSkillMasteryList()
        model.forEach {
            supportSkillMasteryVoList.addSupportSkillMastery(SupportSkillMasteryList.SupportSkillMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.action,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillMasteryVoList
    }

    fun getSupportSkillMasteryDaList(): SupportSkillMasteryList {
        val model = repository.getSupportSkillMasteryDaChart()
        val supportSkillMasteryDaList = SupportSkillMasteryList()
        model.forEach {
            supportSkillMasteryDaList.addSupportSkillMastery(SupportSkillMasteryList.SupportSkillMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.action,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillMasteryDaList
    }

    fun getSupportSkillMasteryViList(): SupportSkillMasteryList {
        val model = repository.getSupportSkillMasteryViChart()
        val supportSkillMasteryViList = SupportSkillMasteryList()
        model.forEach {
            supportSkillMasteryViList.addSupportSkillMastery(SupportSkillMasteryList.SupportSkillMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.action,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillMasteryViList
    }

    fun getSupportSkillMasteryMeList(): SupportSkillMasteryList {
        val model = repository.getSupportSkillMasteryMeChart()
        val supportSkillMasteryMeList = SupportSkillMasteryList()
        model.forEach {
            supportSkillMasteryMeList.addSupportSkillMastery(SupportSkillMasteryList.SupportSkillMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.action,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillMasteryMeList
    }

    fun getSupportSkillMasterySpList(): SupportSkillMasteryList {
        val model = repository.getSupportSkillMasterySpChart()
        val supportSkillMasterySpList = SupportSkillMasteryList()
        model.forEach {
            supportSkillMasterySpList.addSupportSkillMastery(SupportSkillMasteryList.SupportSkillMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.action,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillMasterySpList
    }

    fun getSupportSkillMasteryStaminaList(): SupportSkillMasteryList {
        val model = repository.getSupportSkillMasteryStaminaChart()
        val supportSkillMasteryStaminaList = SupportSkillMasteryList()
        model.forEach {
            supportSkillMasteryStaminaList.addSupportSkillMastery(SupportSkillMasteryList.SupportSkillMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.action,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillMasteryStaminaList
    }

    fun getSupportSkillUnitMasteryList(): SupportSkillUnitMasteryList {
        val model = repository.getSupportSkillUnitMasteryChart()
        val supportSkillUnitMasteryList = SupportSkillUnitMasteryList()
        model.forEach {
            supportSkillUnitMasteryList.addSupportSkillUnitMastery(SupportSkillUnitMasteryList.SupportSkillUnitMastery(
                it.rarity,
                it.cardName,
                it.idea,
                it.inspiration,
                it.increasingStatus,
                it.skillLv,
                it.cardLvReachingToSkillMax
            ))
        }
        return supportSkillUnitMasteryList
    }
}
