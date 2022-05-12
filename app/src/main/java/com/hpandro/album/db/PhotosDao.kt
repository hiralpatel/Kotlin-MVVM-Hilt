package com.hpandro.album.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hpandro.album.model.photos.PhotosItem

/**
 * This interface contains all the database related operation methods.
 */
@Dao
interface PhotosDao {
    /**
     * get photoList from photos_table
     */
    @Query("SELECT * FROM photos_table")
    fun getPhotos(): LiveData<List<PhotosItem?>?>

    /**
     * get selected photos from photos_table
     */
    @Query("SELECT * FROM photos_table WHERE albumId = :id")
    fun getSelectedPhotos(id: Int):  List<PhotosItem?>?

    /**
     * insert selected albums photos in photos_table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(photoItems: List<PhotosItem?>?)
}