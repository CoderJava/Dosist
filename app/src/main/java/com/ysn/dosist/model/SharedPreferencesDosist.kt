/*
 * Created by YSN Studio on 3/23/18 11:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:52 PM
 */

package com.ysn.dosist.model

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/23/18.
 */
class SharedPreferencesDosist @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val ALREADY_SETUP = "ALREADY_SETUP"
    }

    fun putDataInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getDataInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun putDataBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getDataBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun putDataFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getDataFloat(key: String): Float = sharedPreferences.getFloat(key, 0F)

    fun putDataLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun getDataLong(key: String): Long = sharedPreferences.getLong(key, 0L)

    fun putDataString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getDataString(key: String): String = sharedPreferences.getString(key, "")

}