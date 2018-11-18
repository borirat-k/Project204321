package main_chat_application.mainchat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.EditText
import com.android.volley.toolbox.StringRequest
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.self_message.view.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError

import org.json.JSONException
import org.json.JSONObject
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "ChatLog"
    }
    var message: EditText? = null
    val addUrl : String = "localhost/android/addActivity.php" // connect with ???

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = GroupAdapter<ViewHolder>()

        supportActionBar?.title = "Group Chat"

        message = findViewById(R.id.edittext_main)


        Send_button.setOnClickListener{
            val text = edittext_main.text.toString()
            adapter.add(ChatSelfItem(text))
            recycler_message.adapter = adapter
            //Log.d(TAG,"Attempt to send message....")
            edittext_main.setText("")
            addMessage()
        }
    }

    private fun addMessage() {
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
                params.put("message", getMessage)
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
