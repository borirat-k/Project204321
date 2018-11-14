package main_chat_application.mainchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.self_message.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "ChatLog"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Group Chat"

        setupDummyData()

        Send_button.setOnClickListener{
            Log.d(TAG,"Attempt to send message....")

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
    private fun setupDummyData(){
        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(ChatOtherItem("From Hum"))
        adapter.add(ChatSelfItem("To Ea"))
        recycler_message.adapter = adapter
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
