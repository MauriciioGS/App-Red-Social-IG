package com.unam.appredsocialig.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.unam.appredsocialig.BaseFragment
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.FragmentHostLoginBinding
import com.unam.appredsocialig.network.FirestoreService
import com.unam.appredsocialig.ui.NavigationActivity
import com.unam.appredsocialig.util.findNavControllerSafely

const val USERNAME_KEY = "username_key"

class LoginFragment :  BaseFragment<FragmentHostLoginBinding>(
    R.layout.fragment_host_login, FragmentHostLoginBinding::bind) {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestoreService: FirestoreService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        firestoreService = FirestoreService(FirebaseFirestore.getInstance())
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            reload()
        }
    }

    private fun reload() {

    }

    override fun initElements() {
        binding.tvSignUp.setOnClickListener {
            findNavControllerSafely()?.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener {
            showAlert()
            val email = binding.usernameTextField.editText?.text.toString()
            val password = binding.passwordTextField.editText?.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                signIn(email,password)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    startNavMainActivity(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    startNavMainActivity(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun startNavMainActivity(user: FirebaseUser?) {
        val intent = Intent(requireContext(), NavigationActivity::class.java)
        intent.putExtra(USERNAME_KEY, user)
        startActivity(intent)
        hideAlert()
        requireActivity().finish()
    }

    companion object{
        private const val TAG = "EmailPassword"
    }
}