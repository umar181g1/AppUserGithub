package org.andronitysolo.appusergithub.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rimeinder.*
import org.andronitysolo.appusergithub.R
import org.andronitysolo.appusergithub.Riminder.DailyReminder
import org.andronitysolo.appusergithub.SharedPreferences.SharedPreferences

 class RimeinderActivity : AppCompatActivity() {
     lateinit var sharedPreference: SharedPreferences
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rimeinder)
         sharedPreference = SharedPreferences(this)

        dt()

        btDaily.setOnClickListener{
          sharedPreference.daily = btDaily.isChecked
          DailyReminder().setAlarm(this,"09:00",btDaily.isChecked)
        }

    }

    private fun dt(){
       btDaily.isChecked = sharedPreference.daily

    }

}














