package com.bikk.filmlibrary.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bikk.filmlibrary.di.modules.local.RoomModuleInt
import com.bikk.filmlibrary.models.MovieItemModel

class FavoriteViewModel(
    private val roomModuleInt: RoomModuleInt
) : ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItemModel>> {
        return roomModuleInt.allMovies.asLiveData()
    }

}