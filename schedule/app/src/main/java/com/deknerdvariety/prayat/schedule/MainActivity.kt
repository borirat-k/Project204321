package com.deknerdvariety.prayat.schedule

import adapter.MyViewPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fragment.Teach_schedule_fragment
import fragment.test_schedule_fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var adapter:MyViewPagerAdapter ;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //create adapter for viewpager
        adapter = MyViewPagerAdapter(supportFragmentManager)
        //add to list of fragment
        adapter.addFragment(Teach_schedule_fragment(),"teach")
        adapter.addFragment(test_schedule_fragment(),"test")

        //viewPager from main xml file
        viewPager.adapter = adapter
        //create tab of viewpager
        tabs.setupWithViewPager(viewPager)

    }

}
