package com.cmu.cs.cmucats.Assignment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cmu.cs.cmucats.Assignment.MySQL.Downloader
import com.cmu.cs.cmucats.FeatureActivity
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.activity_assignment.*

private var course:String? = null

class AssignmentActivity : NavigationActivity() {

    val TAG_ASSIGNMENT_FRAGMENT = "tag_assignment_fragment"

    var urlAdress: String = "http://10.0.2.2/Project204321/select_assignment.php"
//    var urlAdress: String = "http://10.80.101.74/Project204321/connect.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_assignment)
        val bundle: Bundle = intent.extras
        course = bundle.getString("course")!!

        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_assignment, contentFrameLayout)

        val recyclerView = findViewById(R.id.recycleView_assign) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        add_assignment.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        Downloader(this@AssignmentActivity, urlAdress, recyclerView, course!!).execute()

//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.content_frame, AssignmentFragment(), TAG_ASSIGNMENT_FRAGMENT)
//                .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        finish()
        return super.onNavigationItemSelected(item)
    }

    override fun onBackPressed() {
//        if (supportFragmentManager.findFragmentByTag(TAG_ASSIGNMENT_FRAGMENT)) {
            val intent = Intent(this, FeatureActivity::class.java)
            intent.putExtra("course", course)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
//        }
//        else{
//            supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.content_frame, AssignmentFragment(), TAG_ASSIGNMENT_FRAGMENT)
//                    .commit()
//        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.setTitle(course)
        supportActionBar?.setSubtitle("Assignment")
    }

}

class AssignmentFragment : Fragment(){

    var urlAdress: String = "http://10.0.2.2/Project204321/select_assignment.php"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater.inflate(R.layout.content_assignment, container, false)

        val recyclerView = view.findViewById(R.id.recycleView_assign) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        val fab = view.findViewById(R.id.add_assignment) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        Downloader(view.context, urlAdress, recyclerView, course!!).execute()
        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.setTitle("Assignment")
    }
}
