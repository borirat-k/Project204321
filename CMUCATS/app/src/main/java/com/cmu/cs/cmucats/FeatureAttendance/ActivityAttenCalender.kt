package com.cmu.cs.cmucats.FeatureAttendance

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import java.util.Calendar
import com.cmu.cs.cmucats.R


abstract class ActivityAttenCakendar : AppCompatActivity() {

    abstract internal var Cld: Calendar
    abstract internal var CldV: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Cld = Calendar.getInstance()


        Cld.set(Calendar.MONTH, Calendar.NOVEMBER)
        Cld.set(Calendar.DAY_OF_MONTH, 1)
        Cld.set(Calendar.YEAR, 2018)


        Cld.add(Calendar.DAY_OF_MONTH, 1)
        Cld.add(Calendar.YEAR, 1)


        CldV = findViewById(R.id.Calendarview)

        CldV.setOnDateChangeListener { calendarView, i, i1, i2 ->
            val msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }

    }

}


