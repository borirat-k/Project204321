package com.deknerdvariety.prayat.schedule.ConnectPHP

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class JsonDownloader(var context: Context,private var phpUrl:String,var flagHead:Int) : AsyncTask<Void, Void, String>() {
    private val user_id = 4;
    private lateinit var pd: ProgressDialog

    private fun connect(phpUrl:String):Any{
        try{
            val url = URL(phpUrl)
            val con = url.openConnection() as HttpURLConnection

            con.requestMethod = "POST"
            con.connectTimeout = 10000
            con.readTimeout = 10000
            con.doInput = true
            con.doOutput = true

            return con
        }catch (e:MalformedURLException){
            e.printStackTrace()
            return "URL ERROR" + e.message
        }catch (e:IOException){
            e.printStackTrace()
            return "CONNECT ERROR" + e.message
        }
    }

    private fun download():String{
        val connection = connect(phpUrl)
        if(connection.toString().startsWith("Error")){
            return connection.toString()
        }

        try{
            val con = connection as HttpURLConnection
            val ops = con.outputStream
            val writer = BufferedWriter(OutputStreamWriter(ops,"UTF-8"))
            var data: String = URLEncoder.encode("user_id", "UTF-8") + "=" + URLEncoder.encode(user_id.toString(), "UTF-8")
            writer.write(data)
            writer.flush()
            writer.close()
            ops.close()

            if(con.responseCode == 200){
                val `is` = BufferedInputStream(con.inputStream)
                val br = BufferedReader(InputStreamReader(`is`))
                val jsonData = StringBuffer()
                var line:String?

                do{
                    line = br.readLine()
                    if(line == null){
                        break
                    }

                    //println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj-> $line")
                    jsonData.append(line+"\n")
                }while(true)

                br.close()
                `is`.close()

                println("ffffffffffffffffffffffffffffffff-> $jsonData")

                return jsonData.toString()
            }else{
                return "Error "+con.responseMessage
            }
        }catch (e:IOException){
            return "Error "+e.message
        }
    }

    override fun onPostExecute(jsonData: String) {
        super.onPostExecute(jsonData)
        pd.dismiss()
        if(jsonData!!.startsWith("URL ERROR")){
            val error = jsonData
            Toast.makeText(context,error,Toast.LENGTH_SHORT).show()
        }else if(jsonData.startsWith("connect error")){
            val error = jsonData
            Toast.makeText(context,error,Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"connection and download success,Now attemt to parse....",Toast.LENGTH_SHORT).show()
            JsonParser(context,jsonData,flagHead).execute()
        }
    }

    override fun doInBackground(vararg params: Void): String {

        return download()
    }

    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(context)
        pd.setTitle("Download json")
        pd.setMessage("Message downloading Please wait")
        pd.show()
    }
}



