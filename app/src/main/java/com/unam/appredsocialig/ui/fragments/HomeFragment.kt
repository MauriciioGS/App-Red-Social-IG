package com.unam.appredsocialig.ui.fragments

import androidx.viewpager2.widget.ViewPager2
import com.unam.appredsocialig.BaseFragment
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.FragmentHomeBinding
import com.unam.appredsocialig.util.ViewPagerAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home, FragmentHomeBinding::bind){

    override fun initElements() {
        showCollapsingToolBar(true)
        createFragments()
    }

    private fun createFragments() {
        setUpVP(binding.viewpagerHome)
    }

    private fun setUpVP(viewPager: ViewPager2) {
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
    }
}