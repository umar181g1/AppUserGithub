package org.andronitysolo.appusergithub.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    companion object{
        val Pres_NAME = "org.andronitysolo.appusergithub.SharedPreferences"
        var DAILY = "daily"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(Pres_NAME, 0)

    var daily: Boolean
        get()= prefs.getBoolean(DAILY,false)
        set(value) = prefs.edit().putBoolean(DAILY, value)?.apply()!!


}