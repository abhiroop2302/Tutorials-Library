package com.example.tutorials

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 *  class used for the setting of the adapter for the view pager.
 */
class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList:ArrayList<Fragment>
) : FragmentPagerAdapter (fragmentManager){

    override fun getCount(): Int {
       return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    /**
     *  function check is the current visible page to the user is the last page or not.
     */
    public fun isLastPage(activePos:Int):Boolean{
        return activePos == fragmentList.size-1
    }
}