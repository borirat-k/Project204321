package com.deknerdvariety.prayat.schedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter:PagerAdepterF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        pagerAdapter = PagerAdepterF(supportFragmentManager)
        viewPager.adapter = pagerAdapter

    }
}
