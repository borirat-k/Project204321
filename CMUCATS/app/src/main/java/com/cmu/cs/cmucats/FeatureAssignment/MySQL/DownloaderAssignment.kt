package com.cmu.cs.cmucats.FeatureAssignment.MySQL

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.roger.catloadinglibrary.CatLoadingView
import java.io.*
import java.net.HttpURLConnection
import java.net.URLEncoder

class DownloaderAssignment(private var c: Context, private var urlAdress: String, private var rv: RecyclerView, private var course: String, private var flag: String): AsyncTask<Void, Void, String>() {

    private lateinit var pd: ProgressDialog
    var mView: CatLoadingView? = null

    override fun doInBackground(vararg p0: Void?): String? {
        println("passsssssssssDoInBackground")
        return downloadData()
    }

    override fun onPreExecute() {
        super.onPreExecute()

        mView = CatLoadingView()
        mView!!.show((c as FragmentActivity).supportFragmentManager, "")
//        mView!!.dismiss()

//        pd = ProgressDialog(c)
//        pd.setTitle("Download FeatureAssignment")
//        pd.setMessage("Downloading...Please wait")
////        println("passsssssssssssssssssssss")
//        pd.show()
//        pd.dismiss()
    }

    override fun onPostExecute(jsonData: String?) {
        super.onPostExecute(jsonData)
        println("passssssssssPOSTTTTTTTTBeforee")

//        pd.dismiss()
        mView!!.dismiss()

        println("passssssssssPOSTTTTTTTTAfterrrr")
//        Toast.makeText(c, jsonData.toString(), Toast.LENGTH_SHORT).show()

        if(jsonData==null){
            Toast.makeText(c, "Unsuccessful,No data retrieved", Toast.LENGTH_SHORT).show()
        }
        else{
            //PARSE
            DataParserAssignment(c, jsonData, rv, course, flag).execute()
        }
    }

    private fun downloadData(): String? {
        val con: HttpURLConnection? = Connector().connect(urlAdress)
        if(con==null){
            return null
        }

        try {
            println(course)
            val ops: OutputStream = con.outputStream
            val writer: BufferedWriter = BufferedWriter(OutputStreamWriter(ops, "UTF-8"))
//            val writer: OutputStreamWriter = OutputStreamWriter(con.outputStream)
            var data: String = URLEncoder.encode("Cid", "UTF-8") + "=" + URLEncoder.encode(this.course, "UTF-8")
            writer.write(data)

            writer.flush()

            writer.close()
//            ops.close()
            if (con.responseCode == 200) {
                var inputstream: InputStream = BufferedInputStream(con.inputStream)
                var br: BufferedReader = BufferedReader(InputStreamReader(inputstream))

//                println("BRRRRRRRR = " + br.readLine())

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

                println("JSONNNNNNNN = " + jsonData.toString())
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