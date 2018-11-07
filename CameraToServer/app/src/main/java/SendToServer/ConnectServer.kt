package SendToServer

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ConnectServer(context: Context):AsyncTask<ByteArray, Void, String>() {

//    var context:Context = context
//    lateinit var dialog: AlertDialog
//
//    companion object {
//        val phpfile:String = "192.168.0.102/upload.php"
//    }
//
//    override fun onPreExecute() {
//        super.onPreExecute()
//        dialog = AlertDialog.Builder(context).create()
//        dialog.setTitle("watting upload")
//        dialog.show()
//    }
//
    override fun doInBackground(vararg params: ByteArray?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
//    override fun doInBackground(vararg params: ByteArray): String{
//
//        var result:String = ""
//
//        try {
//
//            val url = URL(phpfile)
//
//            with(url.openConnection() as HttpURLConnection) {
//                // optional default is GET
//                requestMethod = "POST"
//
//                Log.i("ConnecttoServer","doInBackground")
//
//                BufferedReader(InputStreamReader(inputStream)).use {
//                    val response = StringBuffer()
//
//                    var inputLine = it.readLine()
//                    while (inputLine != null) {
//                        response.append(inputLine)
//                        inputLine = it.readLine()
//                    }
//                    println(response.toString())
//
//                    result = response.toString()
//
//                }
//            }
//
//
//        }catch(e: MalformedURLException){
//            result = e.message!!
//        }
//        catch (e: IOException){
//            result = e.message!!
//        }
//
//        return result
//
//    }
//
//    override fun onPostExecute(result: String) {
//        super.onPostExecute(result)
//        dialog.dismiss()
//        dialog.setTitle("upload Successful")
//        dialog.setMessage(result)
//        dialog.show()
//
//    }
}