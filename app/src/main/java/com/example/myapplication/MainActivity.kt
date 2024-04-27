package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.tutorials.PagePositionListener
import com.example.tutorials.ViewPagerManager

class MainActivity : AppCompatActivity() , PagePositionListener{
    private lateinit var binding: ActivityMainBinding
    private val fragmentList:ArrayList<Fragment> = ArrayList()
    private lateinit var viewPagerManger: ViewPagerManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragmentList()
        initAdapter()
        setClickListener()

    }

    private fun setClickListener() {
        binding.btnNext.setOnClickListener {
            if(viewPagerManger.isLastPage()){
                return@setOnClickListener
            }
            viewPagerManger.setCurrentPage(viewPagerManger.getActivePosition()+1)
        }
    }

    private fun initAdapter() {
        viewPagerManger = ViewPagerManager(supportFragmentManager,
            binding.viewPager,
            fragmentList, this)
    }

    private fun initFragmentList() {
        fragmentList.add(Fragment1())
        fragmentList.add(Fragment2())
        fragmentList.add(Fragment3())
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if(viewPagerManger.isLastPage()){
            binding.btnNext.text = "finish"
            return
        }
        binding.btnNext.text = "next"
    }

    override fun onPageSelected(position: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
}