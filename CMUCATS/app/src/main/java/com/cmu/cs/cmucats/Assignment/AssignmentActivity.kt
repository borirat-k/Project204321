package com.cmu.cs.cmucats.Assignment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.cmu.cs.cmucats.Assignment.MySQL.Downloader
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R

import kotlinx.android.synthetic.main.activity_assignment.*

class AssignmentActivity : NavigationActivity() {

    var urlAdress: String = "http://10.0.2.2/connect.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_assignment)
        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_assignment, contentFrameLayout)

        val recyclerView = findViewById(R.id.recycleView_assign) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        Downloader(this@AssignmentActivity, urlAdress, recyclerView).execute()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onNavigationItemSelected(item)
    }

}
