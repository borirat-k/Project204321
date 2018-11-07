package com.cmu.cs.cmucats.Assignment.MySQL

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import java.io.*
import java.net.HttpURLConnection

class Downloader(private var c: Context, private var urlAdress: String, private var rv: RecyclerView): AsyncTask<Void, Void, String>() {

    private lateinit var pd: ProgressDialog

    override fun doInBackground(vararg p0: Void?): String? {
        println("passsssssssssDoInBackground")
        return downloadData()
    }

    override fun onPreExecute() {
        super.onPreExecute()

        pd = ProgressDialog(c)
        pd.setTitle("Download Assignment")
        pd.setMessage("Downloading...Please wait")
//        println("passsssssssssssssssssssss")
        pd.show()
//        pd.dismiss()
    }

    override fun onPostExecute(jsonData: String?) {
        super.onPostExecute(jsonData)
        println("passssssssssPOSTTTTTTTTBeforee")

        pd.dismiss()

        println("passssssssssPOSTTTTTTTTAfterrrr")
//        Toast.makeText(c, jsonData.toString(), Toast.LENGTH_SHORT).show()

        if(jsonData==null){
            Toast.makeText(c, "Unsuccessful,No data retrieved", Toast.LENGTH_SHORT).show()
        }
        else{
            //PARSE
            DataParser(c, jsonData, rv).execute()
        }
    }

    private fun downloadData(): String? {
        val con: HttpURLConnection? = Connector().connect(urlAdress)
        if(con==null){
            return null
        }

        try {
            if (con.responseCode == 200) {
                var inputstream: InputStream = BufferedInputStream(con.inputStream)
                var br: BufferedReader = BufferedReader(InputStreamReader(inputstream))

                var line: String?
                var jsonData: StringBuffer = StringBuffer()

                do {
                    line = br.readLine()

                    if (line == null) {
                        break
                    }

                    jsonData.append(line + "\n")
                } while (true)

                br.close()
                inputstream.close()

                println(jsonData.toString())
                return jsonData.toString()
            }
            else{
                return "Error" + con.responseMessage
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return null
    }
}