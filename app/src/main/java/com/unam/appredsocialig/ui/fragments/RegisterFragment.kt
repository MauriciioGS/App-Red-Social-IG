package com.unam.appredsocialig.ui.fragments

import android.content.Intent
import com.unam.appredsocialig.BaseFragment
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.FragmentSingUpBinding
import com.unam.appredsocialig.ui.NavigationActivity

class RegisterFragment : BaseFragment<FragmentSingUpBinding>(
    R.layout.fragment_sing_up, FragmentSingUpBinding::bind) {

    override fun initElements() {
        binding.btnRegister.setOnClickListener {
            Intent(requireContext(), NavigationActivity::class.java).let(::startActivity)
            requireActivity().finish()
        }
    }
}