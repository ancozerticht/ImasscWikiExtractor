package com.hatenablog.ancozerticht.imassc.domain.repository

import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillGeneral
import com.hatenablog.ancozerticht.imassc.domain.model.SupportSkillOther

interface WikiChartRepository {
    fun getSupportSkillGeneralChart(): List<SupportSkillGeneral>
    fun getSupportSkillOtherChart(): List<SupportSkillOther>
}
