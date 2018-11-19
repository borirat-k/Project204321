package com.deknerdvariety.prayat.schedule

import com.deknerdvariety.prayat.schedule.adapter.MyViewPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deknerdvariety.prayat.schedule.fragment.Teach_schedule_fragment
import com.deknerdvariety.prayat.schedule.fragment.test_schedule_fragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
//    val TAG_URI_PHP = "http://10.80.102.152/project204321/selectSchedule.php"
//    val FlagHead:Int  =1

    companion object {
        lateinit var adapter:MyViewPagerAdapter ;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //create com.deknerdvariety.prayat.schedule.adapter for viewpager
        adapter = MyViewPagerAdapter(supportFragmentManager)
        //add to list of com.deknerdvariety.prayat.schedule.fragment
        adapter.addFragment(Teach_schedule_fragment(),"teach")
        adapter.addFragment(test_schedule_fragment(),"test")

        //viewPager from main xml file
        viewPager.adapter = adapter
        //create tab of viewpager

        tabs.setupWithViewPager(viewPager)
        //JsonDownloader(this,TAG_URI_PHP,FlagHead).execute()
    }



}
