package com.hpandro.album.network

import com.hpandro.album.model.album.AllAlbumsResponse
import com.hpandro.album.model.photos.AllPhotosResponse
import com.hpandro.album.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is interface class contains all the API interface methods
 */
interface AlbumAPIService {
    /**
     * Get the list of Albums
     */
    @GET(Constants.END_POINT_GET_ALBUMS)
    fun getAlbumsResponse(): Observable<AllAlbumsResponse>

    /**
     *  Get the list of photos Albums by ID
     */
    @GET(Constants.END_POINT_GET_PHOTOS)
    fun getPhotosByIDResponse(@Query(Constants.ALBUM_ID) album_id: Int): Observable<AllPhotosResponse>

    /**
     *  Get the list of photos Albums
     */
    @GET(Constants.END_POINT_GET_PHOTOS)
    fun getAllPhotosResponse(): Observable<AllPhotosResponse>
}