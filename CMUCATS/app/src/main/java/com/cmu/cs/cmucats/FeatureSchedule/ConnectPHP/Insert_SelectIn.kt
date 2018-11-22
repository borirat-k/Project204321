package com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP

import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.roger.catloadinglibrary.CatLoadingView
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class Insert_SelectIn (var context: Context, private var phpUrl: String, var semester:String?, var Cid:String,var user_id:Int) : AsyncTask<Void, Void, String>() {
    //private lateinit var mView: CatLoadingView

    private fun connect(phpUrl: String): Any {
        try {
            val url = URL(phpUrl)
            val con = url.openConnection() as HttpURLConnection

            con.requestMethod = "POST"
            con.connectTimeout = 10000
            con.readTimeout = 10000
            con.doInput = true
            con.doOutput = true

            return con
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            return "URL ERROR" + e.message
        } catch (e: IOException) {
            e.printStackTrace()
            return "CONNECT ERROR" + e.message
        }
    }

    private fun download(): String {
        val connection = connect(phpUrl)
        if (connection.toString().startsWith("Error")) {
            return connection.toString()
        }
        try {
            val con = connection as HttpURLConnection
            val ops = con.outputStream
            val writer = BufferedWriter(OutputStreamWriter(ops, "UTF-8"))
            var data: String = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id.toString(), "UTF-8")+"&"+ URLEncoder.encode("semester", "UTF-8")+"="+URLEncoder.encode(semester, "UTF-8")+"&"+URLEncoder.encode("Cid","UTF-8")+"="+URLEncoder.encode(Cid,"UTF-8")
            writer.write(data)
            writer.flush()
            writer.close()
            ops.close()
            if (con.responseCode == 200) {
                var inputstream: InputStream = BufferedInputStream(con.inputStream)
                inputstream.close()
                return ""
            }
            else {
                return "connect error"
            }
            return ""
        } catch (e: IOException) {
            return "Error " + e.message
        }
    }

    override fun onPostExecute(jsonData: String) {
        super.onPostExecute(jsonData)
       // mView.dismiss()
        if (jsonData!!.startsWith("URL ERROR")) {
            val error = jsonData
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        } else if (jsonData.startsWith("connect error")) {
            val error = jsonData
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "connection and download success,Now attemt to parse....", Toast.LENGTH_SHORT).show()
            //JsonParserDetailSchedule(context, jsonData).execute()
        }
    }

    override fun doInBackground(vararg params: Void): String {

        return download()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        //mView = CatLoadingView()
        //mView.show((context as FragmentActivity).supportFragmentManager,"")
    }
}