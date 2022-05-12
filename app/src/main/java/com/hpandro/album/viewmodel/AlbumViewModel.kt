package com.hpandro.album.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hpandro.album.model.album.AlbumItem
import com.hpandro.album.model.photos.PhotosItem
import com.hpandro.album.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


/**
 * This View Model class manages the API and Database operation
 */
@HiltViewModel
class AlbumViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: AlbumRepository

    @Inject
    lateinit var applicationContext: Application

    var albumList = MutableLiveData<ArrayList<AlbumItem>>()
    var photosList = MutableLiveData<ArrayList<PhotosItem>>()

    var albumLiveList: LiveData<List<AlbumItem?>?>? = null
    var photosLiveList: LiveData<List<PhotosItem?>?>? = null

    /**
     * This method get All Live List from the database
     */
    fun init() {
        albumLiveList = repository.getAlbumList()
        photosLiveList = repository.getAllPhotosList()
    }

    /**
     * This method calls API to get list Of Albums from the server
     */
    fun getAlbumListAPI() {
        repository.getAlbumResponse()
            .subscribeOn(Schedulers.io())
            .map { albumResponse ->
                val albumList: ArrayList<AlbumItem> = albumResponse
                repository.insertAllAlbums(albumList)
                albumList
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result: ArrayList<AlbumItem> ->
                albumList.value = result
            }
            ) { error: Throwable ->
                Log.e(
                    TAG,
                    "getAlbums API Error: " + error.message
                )
            }
    }

    /**
     * This method calls API to get list Of Album's photo from the server
     */
    fun getPhotosListAPI() {
        repository.getPhotosResponse()
            .subscribeOn(Schedulers.io())
            .map { photosResponse ->
                val photosList: ArrayList<PhotosItem> = photosResponse
                repository.insertAllPhotos(photosList)
                photosList
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result: ArrayList<PhotosItem> ->
                photosList.value = result
            }
            ) { error: Throwable ->
                Log.e(
                    TAG,
                    "getPhotos API Error: " + error.message
                )
            }
    }

    /**
     * This method calls API to get list Of Album's photo from the server
     */
    fun getPhotosListByIDAPI(id: Int) {
        repository.getPhotosByIDResponse(id)
            .subscribeOn(Schedulers.io())
            .map { photosResponse ->
                val photosList: ArrayList<PhotosItem> = photosResponse
                repository.insertAllPhotos(photosList)
                photosList
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result: ArrayList<PhotosItem> ->
                photosList.value = result
            }
            ) { error: Throwable ->
                Log.e(
                    TAG,
                    "getPhotos API Error: " + error.message
                )
            }
    }

    companion object {
        private const val TAG = "AlbumViewModel"
    }
}