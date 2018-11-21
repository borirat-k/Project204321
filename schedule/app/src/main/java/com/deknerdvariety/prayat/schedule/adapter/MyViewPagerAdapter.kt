package com.deknerdvariety.prayat.schedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MyViewPagerAdapter(manager:FragmentManager): FragmentStatePagerAdapter(manager) {

    private val fragmentList:MutableList<Fragment> = ArrayList()
    private val tabList:MutableList<String> = ArrayList()

    //identify position of com.deknerdvariety.prayat.schedule.fragment
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    //identify total of com.deknerdvariety.prayat.schedule.fragment
    override fun getCount(): Int {
        return fragmentList.size
    }

    //add com.deknerdvariety.prayat.schedule.fragment to list
    fun addFragment(fragment:Fragment,tab_name:String){
        fragmentList.add(fragment)
        tabList.add(tab_name)
    }

    //set up name of tab by tablist what is name of each com.deknerdvariety.prayat.schedule.fragment
    override fun getPageTitle(position:Int):CharSequence?{
        return tabList[position]
    }


}