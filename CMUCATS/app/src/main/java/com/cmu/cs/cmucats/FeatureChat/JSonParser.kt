package com.cmu.cs.cmucats.FeatureChat

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureChat.ChatActivity.Companion.adapter
import com.cmu.cs.cmucats.teacher_id
//import com.cmu.cs.cmucats.FeatureChat.ChatActivity.Companion.adapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class JSonParser(private var c: Context, private var jsonData:String, private var myListView: RecyclerView,var teacher_id:String): AsyncTask<Void, Void, Boolean>() {
    private lateinit var pd: ProgressDialog

    companion object {
        var messageGo = ArrayList<MessageInfo>()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(c)
        pd.setTitle("Parse JSON")
        pd.setMessage("Parsing... Please wait")
    }

    private fun parse(): Boolean {
        try {
            val ja = JSONArray(jsonData)
            var jo = JSONObject()

            for (i in 0 until ja.length()) {
                jo = ja.getJSONObject(i)
                val message_c = jo.getString("Message")
                val time_c = jo.getString("Time")
                val date_c = jo.getString("Date_c")
                val tid = jo.getString("Tid")

                var c = MessageInfo(message_c, date_c, time_c, tid)
                messageGo.add(c)

                //println("$idCourse  $startTime  $stopTime  $firstDay  $secondDay $semester")
            }
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        return parse()
    }
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        pd.dismiss()
        if (result!!) {
            Toast.makeText(c, "successful", Toast.LENGTH_LONG).show()
            val data = JSonParser.messageGo


            for (i in 0..data.size - 1) {
                if ((data[i].Tid) == teacher_id) {
                    adapter.add(ChatSelfItem(data[i].message, data[i].time_c + "น."))
                } else {
                    adapter.add(ChatOtherItem(data[i].message, data[i].Tid.toString(), data[i].time_c + "น."
                            )
                    )
                }
            }
            myListView.scrollToPosition(data.size - 1);
            myListView.adapter = adapter
        } else {
            Toast.makeText(c, "Error", Toast.LENGTH_LONG).show()
        }
    }
}
class MessageInfo(val message: String, val date_c: String, val time_c: String,var Tid:String){}