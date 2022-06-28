package com.bikk.filmlibrary.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bikk.filmlibrary.di.modules.local.RoomModuleInt
import com.bikk.filmlibrary.models.MovieItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val roomModuleInt: RoomModuleInt
) : ViewModel() {

    fun insert(movieItemModel: MovieItemModel) =
        viewModelScope.launch(Dispatchers.IO) {
            roomModuleInt.insertMovies(movieItemModel)

        }

    fun delete(movieItemModel: MovieItemModel) =
        viewModelScope.launch(Dispatchers.IO) {
            roomModuleInt.deleteMovies(movieItemModel)
        }

}