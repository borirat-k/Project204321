package com.deknerdvariety.prayat.connectphpmyadmin

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URLEncoder


class BackgroundWorker(context:Context):AsyncTask<String,Void,String>() {

    var context:Context = context
    lateinit var dialog:AlertDialog

    override fun onPreExecute() {
        super.onPreExecute()
        dialog = AlertDialog.Builder(context).create()
        dialog.setTitle("watting upload")
    }

    override fun doInBackground(vararg params: String): String{

        var result:String = ""
        var url_str:String = params[0]


        try {
            val url = URL(url_str)


//            val ops = http.getOutputStream()
//            val writer = BufferedWriter(OutputStreamWriter(ops,"UTF-8"))

            with(url.openConnection() as HttpURLConnection) {
                // optional default is GET
                requestMethod = "POST"

                Log.i("BackgroundWorker","doInBackground")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println(response.toString())

                    result = response.toString()

                }
            }


        }catch(e:MalformedURLException){
            result = e.message!!
        }
        catch (e:IOException){
            result = e.message!!
        }

        return result

    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        dialog.setTitle("upload Successful")
        dialog.setMessage(result)
        dialog.show()


    }

}