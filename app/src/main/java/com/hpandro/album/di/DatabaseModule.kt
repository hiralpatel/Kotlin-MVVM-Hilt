package com.hpandro.album.di

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.hpandro.album.db.AlbumDao
import com.hpandro.album.db.AppDatabase
import com.hpandro.album.db.PhotosDao
import com.hpandro.album.model.album.AlbumItem
import com.hpandro.album.model.photos.PhotosItem
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import javax.inject.Singleton

/**
 * Provider of singleton objects of Database classes
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    companion object {
        /**
         * Provider of Room Database
         * @return the AppDatabase class
         */
        @Provides
        @Singleton
        fun provideAlbumDB(application: Application?): AppDatabase {
            return Room.databaseBuilder(application!!, AppDatabase::class.java, "Albums")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        /**
         * Provider of AlbumList
         * @return the AlbumList
         */
        @Provides
        @Singleton
        fun provideAlbumDao(albumDao: AlbumDao): LiveData<List<AlbumItem?>?> {
            return albumDao.getAlbum()
        }

        /**
         * Provider of PhotosList
         * @return the PhotosList
         */
        @Provides
        @Singleton
        fun providePhotosDao(photoDao: PhotosDao): LiveData<List<PhotosItem?>?> {
            return photoDao.getPhotos()
        }
    }
}