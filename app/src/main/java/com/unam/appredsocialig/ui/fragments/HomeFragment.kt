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
        initViews()
    }

    private fun initViews() {
        setUpVP(binding.viewpagerHome)

        /* Navigation para botones
        (activity as NavigationActivity).mBinding.ivFilters.setOnSingleClickListener {
            findNavControllerSafely()?.navigate(R.id.action_global_filtersFragment)
        }*/
    }

    private fun setUpVP(viewPager: ViewPager2) {
        val adapter = ViewPagerAdapter(this)
        adapter.addFragment(FeedFragment())
        // Mensajes
        // Historia
        viewPager.adapter = adapter
    }
}