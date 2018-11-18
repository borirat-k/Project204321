package com.cmu.cs.cmucats.FeatureChat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.cmu.cs.cmucats.FeatureActivity
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.self_message.view.*

class ChatActivity : NavigationActivity() {

    private var courseID: String? = null

    companion object {
        val TAG = "ChatLog"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle: Bundle = intent.extras
        courseID = bundle.getString("course")!!
//        setContentView(R.layout.activity_chat)

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_chat, contentFrameLayout)

        val adapter = GroupAdapter<ViewHolder>()

        supportActionBar?.title = "Group Chat"


        send_button_chat.setOnClickListener{
            val text = edittext_main.text.toString()
            adapter.add(ChatSelfItem(text))
            recycler_message.adapter = adapter
            //Log.d(TAG,"Attempt to send message....")
            edittext_main.setText("")
        }
    }
    //class ChatMessage(val text: String)

    //private fun performSendMessage(){
      //  val text = edittext_main.text.toString()
        //val reference = //Connect to Database Ex. FirebaseDatabase.getInstnace().getReference("/messages").push()
        //val chatMesage = ChatMessage(text)
        //reference.setValue(chatMesage)
          //  .addOnSuccessListener{
            //    Log.d(TAG,"Save our chat message: ${reference.key}")
            //}
    //}
//    private fun setupDummyData(text: String){
//        val adapter = GroupAdapter<ViewHolder>()
//        adapter.add(ChatSelfItem(text))
//        recycler_message.adapter = adapter
//    }

    override fun onBackPressed() {
        val intent = Intent(this, FeatureActivity::class.java)
        intent.putExtra("course", courseID)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }
}




class ChatOtherItem(val text:String): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.Message_text.text = text
    }

    override fun getLayout(): Int {
        return R.layout.other_message
    }

}

class ChatSelfItem(val text:String): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.Message_text.text = text
    }

    override fun getLayout(): Int {
        return R.layout.self_message
    }

}
