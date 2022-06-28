package com.bikk.filmlibrary.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikk.filmlibrary.di.modules.RemoteModuleInt
import com.bikk.filmlibrary.models.MoviesModel
import kotlinx.coroutines.*
import retrofit2.Response

class MainViewModel(private val remoteModuleInt: RemoteModuleInt) : ViewModel() {


    private val _mMovies = MutableLiveData<Response<MoviesModel>>()
    val mMovies: LiveData<Response<MoviesModel>> = _mMovies


    fun getMoviesRetrofit() {
        viewModelScope.launch {
            _mMovies.postValue(remoteModuleInt.getMovies())
        }
    }
}