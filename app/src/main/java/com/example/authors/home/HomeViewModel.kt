package com.example.authors.home
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authors.R
import com.example.authors.data.MyApplication
import com.example.authors.data.network.AdItem
import com.example.authors.data.network.AuthorApi
import com.example.authors.data.network.BaseAdapterItem

import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    var currentPage: Int = 1
    val pageLimit: Int = 10

    private val adapterList: ArrayList<BaseAdapterItem> = arrayListOf()

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _statusList = MutableLiveData<List<BaseAdapterItem>>()
    val statusList: LiveData<List<BaseAdapterItem>> = _statusList


    private fun submitList(authors: List<AuthorModel>) {
        adapterList.addAll(authors)
        authors.forEachIndexed { index, baseAdapterItem ->
            if (index % 5 == 0 && index > 0) {
                adapterList.add(index, AdItem(R.drawable.logo))
            }
        }
        _statusList.postValue(adapterList)
    }

    fun loadMore(position: Int) {
        if (adapterList.size - 1 == position) {
            if (adapterList.size >= currentPage * pageLimit) {
                currentPage += 1
                getAuthors(true)
            }
        }
    }

    fun getAuthors(isLoadMore: Boolean = false) {
        if (adapterList.isEmpty() || isLoadMore)
            viewModelScope.launch {
                try {
                    val listResult = AuthorApi.retrofitService.getAuthors(currentPage, pageLimit)
                    _status.value = ""
                    submitList(listResult)
                    MyApplication.getDbInstance()?.insertAuthor(listResult)
                } catch (e: Exception) {
                    val fromDBListResult = MyApplication.getDbInstance()?.getAll() ?: arrayListOf()
                    if (fromDBListResult.isEmpty()) {
                        _status.value = "${e.message}"
                    } else {
                        submitList(fromDBListResult)
                    }
                }
            }
        else {
            _statusList.postValue(adapterList)
        }
    }
}