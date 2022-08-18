package com.example.authors.data

import android.app.Application
import com.example.authors.data.database.AppDataBase
import com.example.authors.data.database.AuthorDao

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        getDataBase = AppDataBase.getInstance(applicationContext).AuthorDao()

    }
    companion object {
        private var getDataBase: AuthorDao? = null
        fun getDbInstance() = getDataBase
    }
}