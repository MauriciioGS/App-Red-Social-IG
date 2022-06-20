package com.unam.appredsocialig.ui.fragments

import com.unam.appredsocialig.BaseFragment
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    R.layout.fragment_search, FragmentSearchBinding::bind
){
    override fun initElements() {
        showCollapsingToolBar()
    }
}