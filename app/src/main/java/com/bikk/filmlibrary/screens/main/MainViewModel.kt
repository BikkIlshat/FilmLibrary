package com.bikk.filmlibrary.screens.main
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.bikk.filmlibrary.data.retrofit.ApiService
import com.bikk.filmlibrary.data.retrofit.paging.MoviesPagingSource


class MainViewModel(private val apiService: ApiService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)) {
        MoviesPagingSource(apiService)

    }.flow.cachedIn(viewModelScope)

}