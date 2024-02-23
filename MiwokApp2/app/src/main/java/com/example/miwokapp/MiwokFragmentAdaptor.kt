package com.example.miwokapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MiwokFragmentAdaptor(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0)
            return ColorFragment()

            else if (position == 1)
            return FamilyFragment()

            else if (position == 2)
            return NumbersFragment()

        else
            return PhraseFragment()
        }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position==0)
            return "Color"
        else if (position==1)
            return "Family"
        else if (position==2)
            return "Numbers"
        else
            return "Phrases"
    }
}