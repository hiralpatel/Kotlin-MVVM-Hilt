package com.hpandro.album.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hpandro.album.model.album.AlbumItem

/**
 * This interface contains all the database related operation methods.
 */
@Dao
interface AlbumDao {
    /**
     * get albumList from album_table
     */
    @Query("SELECT * FROM album_table")
    fun getAlbum(): LiveData<List<AlbumItem?>?>

    /**
     * get selected album from album_table
     */
    @Query("SELECT * FROM album_table WHERE id = :id")
    fun getSelectedAlbum(id: Int): AlbumItem?

    /**
     * insert albums in album_table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAlbum(albumItem: List<AlbumItem?>?)
}