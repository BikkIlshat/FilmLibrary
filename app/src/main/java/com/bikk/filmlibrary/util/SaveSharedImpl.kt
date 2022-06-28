package com.bikk.filmlibrary.util

import android.content.Context
import androidx.preference.PreferenceManager

class SaveSharedImpl : SavedShared {

    override fun setFavorite(context: Context?, key: String, value: Boolean) {
        val setFavoriteShared = context?.let {
            PreferenceManager.getDefaultSharedPreferences(it)
        }
        setFavoriteShared
            ?.edit()
            ?.putBoolean(key, value)
            ?.apply()
    }

    override fun getFavorite(context: Context?, key: String): Boolean {
        val getFavoriteShared = context?.let {
            PreferenceManager.getDefaultSharedPreferences(it)
        }
        return getFavoriteShared
            ?.getBoolean(key, false)
            ?: true
    }
}
