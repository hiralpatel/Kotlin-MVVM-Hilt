package com.hpandro.album.model.photos


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Photos data class for database and parse API response
 */
@Entity(tableName = "photos_table")
data class PhotosItem(
    @SerializedName("albumId")
    val albumId: Int,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    var url: String
)