package com.bikk.filmlibrary.util


interface SavedShared {
    fun setFavorite( key: String, value: Boolean)
    fun getFavorite( key: String): Boolean

}