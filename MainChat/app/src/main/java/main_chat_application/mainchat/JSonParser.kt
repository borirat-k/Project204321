package main_chat_application.mainchat

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

@Suppress("DEPRECATION")
class JSonParser(private var c: Context, private var jsonData:String, private var myListView: RecyclerView): AsyncTask<Void, Void, Boolean>() {
    private lateinit var pd: ProgressDialog
    private var messageGo = ArrayList<MessageInfo>()

    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(c)
        pd.setTitle("Parse JSON")
        pd.setMessage("Parsing... Please wait")
    }

    private fun parse():Boolean{
        try {
            val ja = JSONArray(jsonData)
            var jo = JSONObject()

            for (i in 0 until ja.length()) {
                jo = ja.getJSONObject(i)
                val message_c = jo.getString("Message")
                val time_c = jo.getString("Time")
                val date_c = jo.getString("Date_c")

                var c = MessageInfo(message_c,time_c,date_c)
                messageGo.add(c)

                    //println("$idCourse  $startTime  $stopTime  $firstDay  $secondDay $semester")
                }
            return true
        }catch (e:JSONException){
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
        if(result!!){
            Toast.makeText(c,"successful",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(c,"Error",Toast.LENGTH_LONG).show()
        }
    }

}

class MessageInfo(val message: String, val date_c: String, val time_c: String){}