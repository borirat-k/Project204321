package com.cmu.cs.cmucats

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import com.cmu.cs.cmucats.FeatureCourse.CourseFragment
import com.cmu.cs.cmucats.FeatureCourse.Editprofile
import com.cmu.cs.cmucats.FeatureSchedule.ScheduleActivity
//import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.toolbar_layout.*

var teacher_id: String = ""

open class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG_COURSE_FRAGMENT = "tag_course_fragment"
//    private lateinit var result: Drawer

    lateinit var navigationView: NavigationView
    private lateinit var mDrawerlayout: DrawerLayout
    private lateinit var mToggle: ActionBarDrawerToggle

//    private var containt: FrameLayout? = null
//    private var homeFragment: HomeFragment = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setSupportActionBar(toolbar)
//        if (teacher_id == ""){
//            teacher_id = "3"
//        }
        mDrawerlayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)

        navigationView.setNavigationItemSelectedListener(this)

        mToggle = ActionBarDrawerToggle(this, mDrawerlayout, toolbar, R.string.open, R.string.close)
        mDrawerlayout.addDrawerListener(mToggle)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(false)

//        supportActionBar?.setTitle("Course")

        // คำสั่งเพิ่ม Fragment ลงบน ViewGroup
//        supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.content_frame, CourseFragment().newInstance(), TAG_COURSE_FRAGMENT)
//                .commit()
        navigationView.setCheckedItem(R.id.my_course)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.profile_edit -> {
                // คำสั่งลบ Fragment ที่อยู่บน ViewGroup
//                val fragment = supportFragmentManager.findFragmentByTag(TAG_COURSE_FRAGMENT)
                navigationView.setCheckedItem(R.id.profile_edit)
//                supportFragmentManager.beginTransaction()
//                        .remove(fragment!!)
//                        .commit()
                // ลบทุก fragment
//                for (fragment in supportFragmentManager.fragments) {
//                    supportFragmentManager.beginTransaction().remove(fragment).commit()
//                }
                val intent = Intent(this, Editprofile::class.java)
                startActivity(intent)
                finish()
            }
            R.id.my_course -> {
                navigationView.setCheckedItem(R.id.my_course)
                displayMessage("my course")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                // คำสั่งเพิ่ม Fragment ลงบน ViewGroup
//                supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.content_frame, CourseFragment(), TAG_COURSE_FRAGMENT)
//                        .commit()
//                mDrawerlayout.closeDrawer(GravityCompat.START)
//                supportFragmentManager.beginTransaction().replace(R.id.content_frame,
//                        CourseFragment()).commit()
//                finish()
//                if (item.itemId != R.id.my_course) {
//                    this.finish()
//                    val intent = Intent(this, CourseFragment::class.java)
//                    this.startActivity(intent)
//                }
//                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
//                mDrawerlayout.closeDrawer(GravityCompat.START)
//                mDrawerlayout.openDrawer(GravityCompat.START)
            }
            R.id.schedule -> {
                navigationView.setCheckedItem(R.id.schedule)
                val intent = Intent(this, ScheduleActivity::class.java)
//                intent.putExtra("course", "555555")
                startActivity(intent)
                finish()
            }
            R.id.logout -> {
                navigationView.setCheckedItem(R.id.logout)

            }
            R.id.teacher_praphaporn -> {
                teacher_id = "5"
                navigationView.setCheckedItem(R.id.my_course)
                displayMessage("my course")
//                val intent = Intent(this, MainActivity::class.java)
//                intent.putExtra("check",1)
//                startActivity(intent)
//                finish()
                picture_teacher.setImageResource(R.drawable.prapaporn)
                teacher_name.setText("ดร. ประภาพร เตชอังกูร")
                teacher_email.setText("prapaporn.techaang@cmu.ac.th")
                val myFragment = CourseFragment().newInstance(teacher_id)
                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.content_frame, myFragment)
                transaction.commit()
            }
        }
        mDrawerlayout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun displayMessage(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if (mToggle.onOptionsItemSelected(item)){
//            return true
//        }
        return super.onOptionsItemSelected(item)
    }

//    override fun finish() {
//        super.finish()
//        overridePendingTransition(R.anim.sli, R.anim.abc_fade_out)
//    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
//        if (mDrawerlayout?.isDrawerOpen(GravityCompat.START)!!){
//            mDrawerlayout!!.closeDrawer(GravityCompat.START)
//        }
        else {
            super.onBackPressed()
        }
    }
}




