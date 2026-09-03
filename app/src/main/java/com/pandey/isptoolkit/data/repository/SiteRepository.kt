package com.pandey.isptoolkit.data.repository

import com.pandey.isptoolkit.data.local.dao.SiteDao
import com.pandey.isptoolkit.data.local.entity.SiteEntity
import javax.inject.Inject

class SiteRepository @Inject constructor(
    private val siteDao: SiteDao
) {
    suspend fun saveSite(site: SiteEntity) = siteDao.insertSite(site)

    suspend fun fetchSites(): List<SiteEntity> = siteDao.getAllSites()
}
