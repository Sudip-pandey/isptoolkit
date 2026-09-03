package com.pandey.isptoolkit.data.repository

import com.pandey.isptoolkit.data.local.dao.DeviceDao
import com.pandey.isptoolkit.data.local.entity.DeviceEntity
import javax.inject.Inject

class DeviceRepository @Inject constructor(
    private val deviceDao: DeviceDao
) {
    suspend fun saveDevice(device: DeviceEntity) = deviceDao.insertDevice(device)

    suspend fun fetchDevices(): List<DeviceEntity> = deviceDao.getAllDevices()
}
