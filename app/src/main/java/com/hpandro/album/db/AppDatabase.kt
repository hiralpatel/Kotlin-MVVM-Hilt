package com.hpandro.album.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hpandro.album.model.album.AlbumItem
import com.hpandro.album.model.photos.PhotosItem

/**
 * Database management class
 */
@Database(
    entities = [AlbumItem::class, PhotosItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao?
    abstract fun photoDao(): PhotosDao?
}