package com.pandey.isptoolkit.feature.history

import androidx.lifecycle.ViewModel
import com.pandey.isptoolkit.data.local.dao.SiteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val siteDao: SiteDao
) : ViewModel() {
    val visits = siteDao.getAllVisits()
}