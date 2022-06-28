package com.bikk.filmlibrary.util

import android.content.Context

interface SavedShared {
    fun setFavorite(context: Context?, key: String, value: Boolean)
    fun getFavorite(context: Context?, key: String): Boolean

}