package com.pandey.isptoolkit.data.di

import android.content.Context
import androidx.room.Room
import com.pandey.isptoolkit.data.local.dao.DeviceDao
import com.pandey.isptoolkit.data.local.dao.DiagnosticDao
import com.pandey.isptoolkit.data.local.dao.OntDao
import com.pandey.isptoolkit.data.local.dao.SiteDao
import com.pandey.isptoolkit.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "isp_toolkit_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSiteDao(db: AppDatabase): SiteDao = db.siteDao()

    @Provides
    fun provideDeviceDao(db: AppDatabase): DeviceDao = db.deviceDao()

    @Provides
    fun provideOntDao(db: AppDatabase): OntDao = db.ontDao()

    @Provides
    fun provideDiagnosticDao(db: AppDatabase): DiagnosticDao = db.diagnosticDao()
}