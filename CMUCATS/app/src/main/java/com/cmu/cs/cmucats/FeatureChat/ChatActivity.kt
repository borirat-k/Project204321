package com.cmu.cs.cmucats.FeatureChat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import com.cmu.cs.cmucats.FeatureActivity
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.self_message.view.*
import kotlinx.android.synthetic.main.other_message.view.*
import java.text.SimpleDateFormat
import java.util.*


class ChatActivity : NavigationActivity() {

    private var courseID: String? = null
    private var teacherID: String? = null

    companion object {
        val adapter = GroupAdapter<ViewHolder>()
    }

    val addUrl: String = "http://192.168.1.11/android/insert.php" // connect with ???
    val selectUrl = "http://192.168.1.11/android/select.php"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle = intent.extras
        courseID = bundle.getString("course")!!
        teacherID = bundle.getString("teacher")!!

        var Tid = teacherID.toString()

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_chat, contentFrameLayout)

        val adapter = GroupAdapter<ViewHolder>()
        supportActionBar?.title = "Group Chat"

        JSonDownloader(this,selectUrl,recycler_message).execute()

        send_button_chat.setOnClickListener{
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

    override fun onBackPressed() {
        val intent = Intent(this, FeatureActivity::class.java)
        intent.putExtra("course", courseID)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
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
}




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