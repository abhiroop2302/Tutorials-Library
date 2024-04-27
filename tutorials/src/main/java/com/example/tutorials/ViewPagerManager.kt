package com.example.tutorials

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

/**
 * This class is responsible for the creation of the view pager and bind it with the adapter.
 *
 */
class ViewPagerManager : ViewPager.OnPageChangeListener{

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private var pagePositionListener: PagePositionListener?= null
    private lateinit var viewPager: ViewPager

    private var activePos = -1;

    /**
     *  secondary constructor which is used for the 2 cases ,
     *
     *  1.) if(pagePosListener == null) -> then view pager work will be simple ,
     *  like only the swiping action will be there ,
     *
     *  2.) if(pagePosListener != null) -< then view pager will give the addition information
     *  like the page Position callbacks to the client side to make sure that if the user
     *  want to use that info for doing the some operation or not.
     *
     */
    constructor(
        fragmentManager: FragmentManager,
        viewPager: ViewPager,
        fragmentList: ArrayList<Fragment>,
        pagePositionListener: PagePositionListener?= null
    ) {
        this.pagePositionListener = pagePositionListener
        this.viewPager = viewPager
        initAdapter(viewPager,fragmentManager,fragmentList)
    }

    /**
     *  function is responsible for the creating the adapter and binding the custom adapgter
     *  with the view pager.
     */
    private fun initAdapter(
        viewPager: ViewPager,
        fragmentManager: FragmentManager,
        fragmentList: ArrayList<Fragment>
    ) {
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, fragmentList)
        viewPager.adapter = viewPagerAdapter
        viewPager.setOnPageChangeListener(this)
    }

    /**
     * function which return the result , which consist of the current page visible to the user.
     */
    public fun getActivePosition() = activePos


    /**
     *  function which check is the current visible page to the user is the last page or not.
     */
    public fun isLastPage() = viewPagerAdapter.isLastPage(activePos)

    /**
     *  function responsible for the changing the current page of the view,
     *  used in the case where user want to change the page on the button click , not
     *  on the traditional swiping action.
     */
    public fun setCurrentPage(pos:Int){
        viewPager.currentItem = pos
    }

    /**
     *  below are the callbacks given by the view pager internal for the communication
     *  operation at the client side for the page selection or the scrolled operation happens.
     */

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        activePos = position
        pagePositionListener?.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(position: Int) {
        pagePositionListener?.onPageSelected(position)
    }

    override fun onPageScrollStateChanged(state: Int) {
        pagePositionListener?.onPageScrollStateChanged(state)
    }



}