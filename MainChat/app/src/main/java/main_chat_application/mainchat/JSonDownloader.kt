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

@Suppress("DEPRECATION")
class JSonDownloader(private var c: Context, private var jsonURL: String,var myListView:RecyclerView): AsyncTask<Void, Void, String>(){

    private lateinit var pd: ProgressDialog
    val Gid = "001"
    val Tid = 1

    private fun connect(jsonURL: String):Any{
        try{
            val url = URL(jsonURL)
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "POST"
            con.connectTimeout = 15000
            con.readTimeout = 15000
            con.doInput = true

            return con

        }catch(e: MalformedURLException){
            e.printStackTrace()
            return "URL Error"+e.message
        }catch(e: IOException){
            e.printStackTrace()
            return "Connect Error" + e.message
        }
    }


    private fun download():String{
        val connection = connect(jsonURL)
        if(connection.toString().startsWith("Error")){
            return connection.toString()
        }
        try{
            val con =  connection as HttpURLConnection
            val ops = con.outputStream
            val writer = BufferedWriter(OutputStreamWriter(ops,"UTF-8"))
            var data: String =URLEncoder.encode("Gid", "UTF-8") + "=" + URLEncoder.encode(Gid, "UTF-8")
            writer.write(data)
            writer.flush()
            writer.close()
            ops.close()

            if(con.responseCode == 200){
                val bufcon = BufferedInputStream(con.inputStream)  // `is` = bufcon
                val br = BufferedReader(InputStreamReader(bufcon))
                val jsonData = StringBuffer()
                var line:String?

                do {
                    line = br.readLine()
                    if(line == null){break}
                    jsonData.append(line+"\n")

                }while (true)

                br.close()
                bufcon.close()

                return jsonData.toString()


            }else{
                return "Error "+con.responseMessage
            }

        }catch (e: IOException){
            e.printStackTrace()
            return "Error " + e.message
        }
    }

    override fun onPreExecute() {
        super.onPreExecute()
        pd = ProgressDialog(c)
        pd.setTitle("Download JSON")
        pd.setMessage("Downloading.. Please wait")
        pd.show()
    }

    override fun doInBackground(vararg params: Void?): String {
        return download()
    }

    override fun onPostExecute(jsonData: String) {
        super.onPostExecute(jsonData)

        pd.dismiss()
        if(jsonData.startsWith("URL ERROR")){
            val error = jsonData
            Toast.makeText(c,error,Toast.LENGTH_LONG).show()
            Toast.makeText(c,"MOST PROBABLY APP CANNOT CONNECT DUE TO WRONG ...",Toast.LENGTH_LONG).show()
        }else if(jsonData.startsWith("Connect ERROR")){
            val error = jsonData
            Toast.makeText(c,error,Toast.LENGTH_LONG).show()
            Toast.makeText(c,"MOST PROBABLY APP CANNOT CONNECT To any network ...",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(c,"Network Connection and download successful", Toast.LENGTH_LONG).show()
            JSonParser(c,jsonData,myListView).execute()
        }
    }

}

