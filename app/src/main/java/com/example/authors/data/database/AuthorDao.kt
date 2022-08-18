package com.example.authors.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.authors.home.AuthorModel


@Dao
interface AuthorDao {

    @Query("SELECT * FROM Author")
    suspend fun getAll(): List<AuthorModel>

    @Insert
    fun insertAuthor(authors: List<AuthorModel>)
}