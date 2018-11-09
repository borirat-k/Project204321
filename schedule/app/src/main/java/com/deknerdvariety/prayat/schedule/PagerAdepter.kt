package com.deknerdvariety.prayat.schedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.os.Bundle



class PagerAdepterF(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {

    companion object {
        final val ARGS_POSITION = "name"
    }

    override fun getItem(position: Int): Fragment {
        val fragment = Teach_schedule()
        val args = Bundle()
        args.putInt(ARGS_POSITION, position)
        fragment.arguments = args
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }
}