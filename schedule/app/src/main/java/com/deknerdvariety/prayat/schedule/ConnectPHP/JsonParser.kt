package com.deknerdvariety.prayat.schedule.ConnectPHP

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.deknerdvariety.prayat.schedule.R
import com.deknerdvariety.prayat.schedule.R.id.*
import com.deknerdvariety.prayat.schedule.adapter.TeachHeadingAdapter
import com.deknerdvariety.prayat.schedule.fragment.Teach_schedule_fragment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

@Suppress("DEPRECATION")
class JsonParser(var context: Context, private var jsonData: String,var rv:RecyclerView,var flagHead:Int) : AsyncTask<Void, Void, Boolean>() {


    private lateinit var pd: ProgressDialog
    private var courseScheduleL = ArrayList<CourseScheduleSelect>()
    private val headScheduleList = ArrayList<HeadScedule>()

    private fun cal_Time(time_teach:String):ArrayList<String>{
        val list = ArrayList<String>()
        list.clear()
        var startTime = ""
        for(i in 0..4){
            startTime += time_teach[i]
        }
        var stopTime = ""
        for(i in 6..10){
            stopTime += time_teach[i]
        }
        list.add(startTime)
        list.add(stopTime)

        return list
    }

    private fun cal_date(date_teach:String):ArrayList<String>{
        val list = ArrayList<String>()
        list.clear()
        var firstDay = ""
        if(date_teach[0].toLowerCase().equals('m'))
            firstDay = "Monday"
        else if(date_teach[0].toLowerCase().equals('t'))
            firstDay = "Tuesday"
        else if(date_teach[0].toLowerCase().equals('w'))
            firstDay = "Wednesday"

        var secondDay =""
        if(date_teach[date_teach.lastIndex].toLowerCase().equals('f'))
            secondDay = "Friday"
        else if(date_teach[date_teach.lastIndex].toLowerCase().equals('h'))
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
                if(flagHead == 1){
                    val tc_id = jo.getInt("tc_id")
                    val semester = jo.getString("semester")

                    var h = HeadScedule(tc_id,semester)
                    headScheduleList.add(h)
                }
                else{
                    val idCourse = jo.getString("idCourse")
                    val time_teach = jo.getString("time_teach")
                    val date_teach = jo.getString("date_teach")
                    val semester = jo.getString("semester")

                    var startTime = cal_Time(time_teach)[0]
                    var stopTime = cal_Time(time_teach)[1]

                    var firstDay = cal_date(date_teach)[0]
                    var secondDay = cal_date(date_teach)[1]

                    var c = CourseScheduleSelect(semester,idCourse,startTime,stopTime,firstDay,secondDay)
                    courseScheduleL.add(c)

                    println("$idCourse  $startTime  $stopTime  $firstDay  $secondDay $semester")
                }

            }

            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }


    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        pd.dismiss()
        if (result!!) {
            //Toast.makeText(context,"adapter pass",Toast.LENGTH_SHORT).show()
            //var view = LayoutInflater.from(context).inflate(R.layout.downloadphp,viewGroup,false)
            Teach_schedule_fragment.course_schedule_Head = headScheduleList
            rv.adapter = TeachHeadingAdapter(headScheduleList,context)
           // Toast.makeText(context,"ddddddddddddddddddddddddddddddddd",Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(context, "this is data that we try to parse ${jsonData}", Toast.LENGTH_SHORT).show()
        }

    }

    override fun doInBackground(vararg params: Void?): Boolean {
        return parse()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(context)
        pd.setTitle("Parse Json")
        pd.setMessage("Parsing...Please wait")
        pd.show()

    }
}

class CourseScheduleSelect(val semester: String?, val idCourse: String?, val startTime: String?, val stopTime: String?, val firstDay: String?, val secondDay: String?) {}

class HeadScedule(val tc_id:Int,val semester:String)
