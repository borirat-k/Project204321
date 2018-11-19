package main_chat_application.mainchat

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.sql.Time


@Suppress("DEPRECATION")
class InsertMessage(private var context: Context,
                    private var phpUrl: String,
                    private val Tid:Int,
                    private val Gid:String,
                    private val Message:String,
                    private val Date_c:String,
                    private val time_c:String): AsyncTask<Void, Void, String>(){

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
        }catch (e: MalformedURLException){
            e.printStackTrace()
            return "URL ERROR" + e.message
        }catch (e: IOException){
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
            var data: String = URLEncoder.encode("Tid", "UTF-8") + "=" + URLEncoder.encode(Tid.toString(), "UTF-8")
            data+="&"+URLEncoder.encode("Gid", "UTF-8") + "=" + URLEncoder.encode(Gid, "UTF-8")
            data+="&"+URLEncoder.encode("Message", "UTF-8") + "=" + URLEncoder.encode(Message, "UTF-8")
            data+="&"+URLEncoder.encode("Date_c", "UTF-8") + "=" + URLEncoder.encode(Date_c, "UTF-8")
            data+="&"+URLEncoder.encode("Time", "UTF-8") + "=" + URLEncoder.encode(time_c, "UTF-8")
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

                return jsonData.toString()
            }else{
                return "Error "+con.responseMessage
            }
        }catch (e: IOException){
            return "Error "+e.message
        }
    }

    override fun onPostExecute(jsonData: String) {
        super.onPostExecute(jsonData)
        pd.dismiss()
        if(jsonData!!.startsWith("URL ERROR")){
            val error = jsonData
            Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
        }else if(jsonData.startsWith("connect error")){
            val error = jsonData
            Toast.makeText(context,error, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"connection and download success,Now attemt to parse....", Toast.LENGTH_SHORT).show()
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
    }
}




