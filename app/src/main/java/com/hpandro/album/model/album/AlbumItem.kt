package com.hpandro.album.model.album

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Album data class for database class and parse API response
 */
@Entity(tableName = "album_table")
data class AlbumItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)