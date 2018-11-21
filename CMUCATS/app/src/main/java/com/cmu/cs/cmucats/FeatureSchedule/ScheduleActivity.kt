package com.cmu.cs.cmucats.FeatureSchedule

import android.os.Bundle
import android.widget.FrameLayout
import com.cmu.cs.cmucats.FeatureSchedule.ConnectPHP.JsonDownloadSchedule
import com.cmu.cs.cmucats.FeatureSchedule.adapter.MyViewPagerAdapter
import com.cmu.cs.cmucats.FeatureSchedule.fragment.Teach_schedule_fragment
import com.cmu.cs.cmucats.FeatureSchedule.fragment.test_schedule_fragment
import com.cmu.cs.cmucats.NavigationActivity
import com.cmu.cs.cmucats.R
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.teach_schedule_fragment.view.*

class ScheduleActivity : NavigationActivity() {

    lateinit var adapter: MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_schedule)
        val contentFrameLayout: FrameLayout = findViewById<FrameLayout>(R.id.content_frame)
        layoutInflater.inflate(R.layout.activity_schedule, contentFrameLayout)
        //create adapter for viewpager
        adapter = MyViewPagerAdapter(supportFragmentManager)
        //add to list of fragment
        adapter.addFragment(Teach_schedule_fragment(),"teach")
        adapter.addFragment(test_schedule_fragment(),"test")
        //viewPager from main xml file
        viewPager.adapter = adapter
        //create tab of viewpager
        tabs.setupWithViewPager(viewPager)
        navigationView.setCheckedItem(R.id.schedule)

    }
}