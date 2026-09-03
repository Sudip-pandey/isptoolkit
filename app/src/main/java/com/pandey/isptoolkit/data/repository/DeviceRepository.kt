package com.pandey.isptoolkit.data.repository

import com.pandey.isptoolkit.data.local.dao.DeviceDao
import com.pandey.isptoolkit.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeviceRepository @Inject constructor(
    private val deviceDao: DeviceDao
) {
    suspend fun saveDevice(device: DeviceEntity) = deviceDao.insertDevices(listOf(device))

    suspend fun fetchDevices(): List<DeviceEntity> = deviceDao.getAllDevices().first()
}
