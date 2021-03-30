package com.hatenablog.ancozerticht.imassc.domain.repository

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillMastery
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillOther
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillUnitMastery

interface WikiChartRepository {
    fun getSupportSkillGeneralChart(): List<SupportSkillGeneral>
    fun getSupportSkillOtherChart(): List<SupportSkillOther>
    fun getSupportSkillMasteryVoChart(): List<SupportSkillMastery>
    fun getSupportSkillMasteryDaChart(): List<SupportSkillMastery>
    fun getSupportSkillMasteryViChart(): List<SupportSkillMastery>
    fun getSupportSkillMasteryMeChart(): List<SupportSkillMastery>
    fun getSupportSkillMasterySpChart(): List<SupportSkillMastery>
    fun getSupportSkillMasteryStaminaChart(): List<SupportSkillMastery>
    fun getSupportSkillUnitMasteryChart(): List<SupportSkillUnitMastery>
}
