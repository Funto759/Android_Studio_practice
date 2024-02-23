package com.example.nigeriaguide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class mainPageViewAdaptor (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 0
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0 )
            return AbujaFragment()
        else
            return LagosFragment()
    }

}