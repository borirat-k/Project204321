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
class JsonParserSchedule(var context: Context, private var jsonData: String, var rv: RecyclerView) : AsyncTask<Void, Void, Boolean>() {


    private lateinit var pd: ProgressDialog
    private val headScheduleList = ArrayList<HeadScedule>()

    private fun parse(): Boolean {
        try {
            val ja = JSONArray(jsonData)
            var jo = JSONObject()

            for (i in 0 until ja.length()) {
                jo = ja.getJSONObject(i)

                val tc_id = jo.getInt("tc_id")
                val semester = jo.getString("semester")

                var h = HeadScedule(tc_id, semester)
                headScheduleList.add(h)
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
            rv.adapter = TeachHeadingAdapter(headScheduleList, context)
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

class HeadScedule(val tc_id: Int, val semester: String)
