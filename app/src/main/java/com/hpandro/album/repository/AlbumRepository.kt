package com.hpandro.album.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.hpandro.album.db.AppDatabase
import com.hpandro.album.model.album.AllAlbumsResponse
import com.hpandro.album.model.album.AlbumItem
import com.hpandro.album.model.photos.AllPhotosResponse
import com.hpandro.album.model.photos.PhotosItem
import com.hpandro.album.network.AlbumAPIService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is repository class to manage the database and API's operation
 */
@Singleton
class AlbumRepository @Inject constructor() {
    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var apiService: AlbumAPIService

    @Inject
    lateinit var applicationContext: Application

    /**
     *  GET API to get List of Albums
     */
    fun getAlbumResponse(): Observable<AllAlbumsResponse> {
        return apiService.getAlbumsResponse()
    }

    /**
     *Insert Albums in AlbumTable details in database
     */
    fun insertAllAlbums(albumList: List<AlbumItem?>?) {
        appDatabase.albumDao()!!.insertAllAlbum(albumList)
    }

    /**
     *  Get list of Albums from the database
     */
    fun getAlbumList(): LiveData<List<AlbumItem?>?> {
        return appDatabase.albumDao()!!.getAlbum()
    }

    /**
     * GET API to get List of Album Photos Response
     */
    fun getPhotosResponse(): Observable<AllPhotosResponse> {
        return apiService.getAllPhotosResponse()
    }

    /**
     * GET API to get List of Album Photos Response
     */
    fun getPhotosByIDResponse(id: Int): Observable<AllPhotosResponse> {
        return apiService.getPhotosByIDResponse(id)
    }

    /**
     * Insert Album photos in database
     */
    fun insertAllPhotos(photosList: List<PhotosItem?>?) {
        appDatabase.photoDao()!!.insertAllPhotos(photosList)
    }

    /**
     * Get list of Album Photos from the database
     */
    fun getAllPhotosList(): LiveData<List<PhotosItem?>?> {
        return appDatabase.photoDao()!!.getPhotos()
    }

    /**
     * Get list of Album Photos from the database by selected id
     */
    fun getPhotosListByID(id: Int): List<PhotosItem?>? {
        return appDatabase.photoDao()!!.getSelectedPhotos(id)
    }
}