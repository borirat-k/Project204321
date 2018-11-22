package com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.CalendarView
import android.widget.Toast
import com.roger.catloadinglibrary.CatLoadingView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Suppress("DEPRECATION")
class JsonParserDetailSchedule(var context: Context, private var jsonData: String) : AsyncTask<Void, Void, Boolean>() {

    private lateinit var mView:CatLoadingView
    companion object {
        var courseScheduleL = ArrayList<CourseScheduleSelect>()
    }

    private fun cal_Time(time_teach: String): ArrayList<String> {
        val list = ArrayList<String>()
        list.clear()
        var startTime = ""
        for (i in 0..4) {
            startTime += time_teach[i]
        }
        var stopTime = ""
        for (i in 6..10) {
            stopTime += time_teach[i]
        }
        list.add(startTime)
        list.add(stopTime)

        return list
    }

    private fun cal_date(date_teach: String): ArrayList<String> {
        val list = ArrayList<String>()
        list.clear()
        var firstDay = ""
        if (date_teach[0].toLowerCase().equals('m'))
            firstDay = "Monday"
        else if (date_teach[0].toLowerCase().equals('t'))
            firstDay = "Tuesday"
        else if (date_teach[0].toLowerCase().equals('w'))
            firstDay = "Wednesday"

        var secondDay = ""
        if (date_teach[date_teach.lastIndex].toLowerCase().equals('f'))
            secondDay = "Friday"
        else if (date_teach[date_teach.lastIndex].toLowerCase().equals('h'))
            secondDay = "Thursday"

        list.add(firstDay)
        list.add(secondDay)

        return list
    }


    private fun parse(): Boolean {
        try {
            val ja = JSONArray(jsonData)
            var jo = JSONObject()

            for (i in 0 until ja.length()) {
                jo = ja.getJSONObject(i)


                val idCourse = jo.getString("idCourse")
                val time_teach = jo.getString("time_teach")
                val date_teach = jo.getString("date_teach")
                val semester = jo.getString("semester")

                var startTime = cal_Time(time_teach)[0]
                var stopTime = cal_Time(time_teach)[1]

                var firstDay = cal_date(date_teach)[0]
                var secondDay = cal_date(date_teach)[1]

                var c = CourseScheduleSelect(semester, idCourse, startTime, stopTime, firstDay, secondDay)
                courseScheduleL.add(c)

                println("$idCourse  $startTime  $stopTime  $firstDay  $secondDay $semester")


            }

            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }


    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        mView.dismiss()
        if (result!!) {
            //Toast.makeText(context,"adapter pass",Toast.LENGTH_SHORT).show()
            //var view = LayoutInflater.from(context).inflate(R.layout.downloadphp,viewGroup,false)
            // Toast.makeText(context,"ddddddddddddddddddddddddddddddddd",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,"parse successful",Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(context, "this is data that we try to parse ${jsonData}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun doInBackground(vararg params: Void?): Boolean {
        return parse()
    }
    override fun onPreExecute() {
        super.onPreExecute()
        mView = CatLoadingView()
        mView.show((context as FragmentActivity).supportFragmentManager,"")
    }
}
class CourseScheduleSelect(val semester: String?, val idCourse: String?, val startTime: String?, val stopTime: String?, val firstDay: String?, val secondDay: String?) {}
