package main_chat_application.mainchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Group Chat"
        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(ChatOtherItem())
        adapter.add(ChatSelfItem())
        adapter.add(ChatOtherItem())
        adapter.add(ChatSelfItem())
        adapter.add(ChatOtherItem())
        adapter.add(ChatSelfItem())
        adapter.add(ChatOtherItem())
        adapter.add(ChatSelfItem())
        adapter.add(ChatOtherItem())
        adapter.add(ChatSelfItem())




        recycler_message.adapter = adapter

    }
}


class ChatOtherItem: Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.other_message
    }

}

class ChatSelfItem: Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.self_message
    }

}
