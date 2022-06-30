package com.bikk.filmlibrary.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikk.filmlibrary.di.modules.local.RoomModuleInt
import com.bikk.filmlibrary.di.modules.remote.RemoteModuleInt
import com.bikk.filmlibrary.models.MovieItemModel
import com.bikk.filmlibrary.models.actors.ActorsModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailsViewModel(
    private val roomModuleInt: RoomModuleInt,
    private val remoteModuleInt: RemoteModuleInt
) : ViewModel() {

    private val _actors = MutableLiveData<Response<ActorsModels>>()
    val actors: LiveData<Response<ActorsModels>> = _actors

    fun getActors(id: Int) {
        viewModelScope.launch {
            _actors.postValue(remoteModuleInt.getActorsMovie(id))
        }
    }

        fun insert(movieItemModel: MovieItemModel) =
            viewModelScope.launch(Dispatchers.IO) {
                roomModuleInt.insertMovies(movieItemModel)

            }

        fun delete(movieItemModel: MovieItemModel) =
            viewModelScope.launch(Dispatchers.IO) {
                roomModuleInt.deleteMovies(movieItemModel)
            }

}