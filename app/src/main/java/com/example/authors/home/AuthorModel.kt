package com.example.authors.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.authors.data.network.BaseAdapterItem
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "Author")
data class AuthorModel(
    @PrimaryKey
    @Json(name = "id") val authorId: String,
    @Json(name = "author") val authorName: String?,
    @Json(name = "width") val authorWidth: String?,
    @Json(name = "height") val authorHeight: String?,
    @Json(name = "url") val authorUrl: String?,
    @Json(name = "download_url") val authorDownloadUrl: String?
) : BaseAdapterItem(), Serializable

