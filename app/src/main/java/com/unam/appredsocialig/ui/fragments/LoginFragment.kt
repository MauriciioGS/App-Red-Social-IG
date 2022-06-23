package com.unam.appredsocialig.ui.fragments

import android.content.Intent
import com.unam.appredsocialig.BaseFragment
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.FragmentHostLoginBinding
import com.unam.appredsocialig.ui.NavigationActivity
import com.unam.appredsocialig.util.findNavControllerSafely

class LoginFragment : BaseFragment<FragmentHostLoginBinding>(
    R.layout.fragment_host_login, FragmentHostLoginBinding::bind) {

    override fun initElements() {
        binding.btnLogin.setOnClickListener {
            Intent(requireContext(),NavigationActivity::class.java).let(::startActivity)
            requireActivity().finish()
        }
        binding.btnSignup.setOnClickListener {
            findNavControllerSafely()?.navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }
}