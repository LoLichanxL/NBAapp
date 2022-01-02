package com.bobnevpavel.nbanews.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {
    private lateinit var binding:FragmentSignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        subscribeOnLoginButton()
        return binding.root
    }

    private fun subscribeOnLoginButton(){
        binding.loginButton.setOnClickListener(View.OnClickListener{
            if (!binding.emailEditText.text.isEmpty()){
                if (!binding.passwordEditText.text.isEmpty()){
                    signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                }else{
                    Snackbar.make(binding.root, "Please enter a password", Snackbar.LENGTH_LONG).show()
                }
            }else{
                Snackbar.make(binding.root, "Please enter an email", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun signIn(email:String, password:String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnSuccessListener {
            findNavController().navigate(R.id.action_login_fragment_to_main_screen)
        }.addOnFailureListener {
            Snackbar.make(binding.root, "Sign In is failed", Snackbar.LENGTH_LONG).show()
        }
    }
}