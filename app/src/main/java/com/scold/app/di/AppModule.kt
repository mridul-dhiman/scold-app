package com.scold.app.di

import android.content.Context
import androidx.room.Room
import com.scold.app.data.local.PreferencesManager
import com.scold.app.data.local.ScoldDatabase
import com.scold.app.data.local.SessionDao
import com.scold.app.data.repository.PostRepository
import com.scold.app.data.repository.PostRepositoryImpl
import com.scold.app.data.repository.SessionRepository
import com.scold.app.data.repository.SessionRepositoryImpl
import com.scold.app.data.repository.UserRepository
import com.scold.app.data.repository.UserRepositoryImpl
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
    fun provideScoldDatabase(@ApplicationContext context: Context): ScoldDatabase {
        return Room.databaseBuilder(
            context,
            ScoldDatabase::class.java,
            "scold_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSessionDao(database: ScoldDatabase): SessionDao {
        return database.sessionDao()
    }

    @Provides
    @Singleton
    fun provideSessionRepository(sessionDao: SessionDao): SessionRepository {
        return SessionRepositoryImpl(sessionDao)
    }

    @Provides
    @Singleton
    fun provideUserRepository(preferencesManager: PreferencesManager): UserRepository {
        return UserRepositoryImpl(preferencesManager)
    }

    @Provides
    @Singleton
    fun providePostRepository(): PostRepository {
        return PostRepositoryImpl()
    }
}
