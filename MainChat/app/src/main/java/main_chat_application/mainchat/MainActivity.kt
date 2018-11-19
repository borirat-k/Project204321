package main_chat_application.mainchat

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.self_message.view.*
import kotlinx.android.synthetic.main.other_message.view.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        val adapter = GroupAdapter<ViewHolder>()
    }

    val message: EditText? = null
    val addUrl: String = "http://192.168.1.11/android/insert.php" // connect with ???
    val selectUrl = "http://192.168.1.11/android/select.php"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.title = "Group Chat"
        //JSonDownloader(context,addUrl,recycler_message).execute()
        var Tid = 1
        var Gid = "001"

        JSonDownloader(this,selectUrl,recycler_message).execute()




        Send_button.setOnClickListener {
            var Date_c:String = aboutDate()
            var time_c:String = aboutTime()
            val text = edittext_main?.text.toString()
            if (text != "") {
                //addMessage()
                adapter.add(ChatSelfItem(text,time_c+" น."))
//                adapter.add(ChatOtherItem("Yes I am","Teacher 1",aboutTime())) // Text,Time And Name must pull from db
                recycler_message.adapter = adapter
//                //Log.d(TAG,"Attempt to send message...."
                resetInput()
                InsertMessage(this,addUrl,Tid,Gid,text,Date_c,time_c).execute()
            }
        }

    }


    private fun aboutDate():String{
        var c = Calendar.getInstance();
        var df : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        var formattedDate:String = df.format(c.getTime())
        return formattedDate
    }
    private fun aboutTime():String{
        // เห็นเวลา
        var c = Calendar.getInstance();
        var df : SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
        var formattedDate:String = df.format(c.getTime())
        return formattedDate
    }
    private fun resetInput() {
        // Clean text box
        edittext_main.text.clear()
        //hide text box
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    /*private fun addMessage() {
        val getMessage = message?.text.toString()

            val stringRequest = object : StringRequest(Request.Method.POST,addUrl,Response.Listener<String>{
                response ->
            try {
                val obj = JSONObject(response)
                Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()
            }catch (e: JSONException){
                e.printStackTrace()
            }

        }, object : Response.ErrorListener{
            override fun onErrorResponse(volleyError: VolleyError) {
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            }
        }){
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["message"] = getMessage
                params["Gid"] = "001"
                params["Tid"] = "1"
                params["Date_c"] = "2018-08-10"
                params["Time"] = "16:41"

                return params
            }
        }
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
}
    //class ChatMessage(val text: String)

    //private fun performSendMessage(){
      //  val text = edittext_main.text.toString()
        //val reference = //Connect to Database Ex. FirebaseDatabase.getInstnace().getReference("/messages").push()
        //val chatMessage = ChatMessage(text)
        //reference.setValue(chatMessage)
          //  .addOnSuccessListener{
            //    Log.d(TAG,"Save our chat message: ${reference.key}")
            //}
    //}
//    private fun setupDummyData(text: String){
//        val adapter = GroupAdapter<ViewHolder>()
//        adapter.add(ChatSelfItem(text))
//        recycler_message.adapter = adapter
//    }*/


    class ChatOtherItem(val text: String, val textName:String, val textTime:String) : Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.Message_text.text = text
            viewHolder.itemView.txtOtherUser.text = textName
            viewHolder.itemView.txtOtherMessageTime.text = textTime
        }

        override fun getLayout(): Int {
            return R.layout.other_message
        }

    }

    class ChatSelfItem(val text: String,val textTime:String) : Item<ViewHolder>() {
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.self_message_text.text = text
            viewHolder.itemView.txtMyMessageTime.text =textTime
        }

        override fun getLayout(): Int {
            return R.layout.self_message
        }

    }
}
