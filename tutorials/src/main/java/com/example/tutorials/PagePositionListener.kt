package com.example.tutorials


/**
 *  interface which is responsible for the communication to the client
 *  side when the page is changed by the user using the swipe action or the some thing else.
 */
interface PagePositionListener {
    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
    fun onPageSelected(position: Int)
    fun onPageScrollStateChanged(state: Int)
}