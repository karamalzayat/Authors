package com.example.authors.data.network

import com.example.authors.home.AuthorModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// detecting the base url
private const val BASE_URL =
    "https://picsum.photos"

// creating retrofit builder and adding Moshi converter to convert from json to strings by bass baseUrl
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface AuthorsApiService {
    //this fun responsable for getting the Authors data
    @GET("/v2/list")
    suspend fun getAuthors(
        @Query("page") number: Int = 1,
        @Query("limit") limit: Int = 10
    ): List<AuthorModel>
}

// we use object declaration to make sure that we made only one instance from Retrofit
// we use lazy to make sure that retrofitService will not be init until using it
//from the two hints above, now we can safe call retrofit,create()
object AuthorApi {
    val retrofitService: AuthorsApiService by lazy {
        retrofit.create(AuthorsApiService::class.java)
    }
}