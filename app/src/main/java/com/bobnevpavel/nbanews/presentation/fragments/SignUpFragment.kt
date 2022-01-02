package com.bobnevpavel.nbanews.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bobnevpavel.nbanews.R
import com.bobnevpavel.nbanews.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        subscribeOnLoginButton()
        return binding.root
    }
    private fun subscribeOnLoginButton(){
        binding.loginButton.setOnClickListener(View.OnClickListener {
            if (!binding.emailEditText.text.isEmpty()){
                if (!binding.passwordEditText.text.isEmpty()){
                    if (binding.passwordEditText.text.toString().equals(binding.secondPasswordEditText.text.toString())){
                        createAccount(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                    }else {
                        Snackbar.make(binding.root, "Passwords must be equals", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
    private fun createAccount(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(OnSuccessListener {
            findNavController().navigate(R.id.action_login_fragment_to_main_screen)
        }).addOnFailureListener(OnFailureListener {
            Snackbar.make(binding.root, "SignUp is failure", Snackbar.LENGTH_LONG).show()
        })
    }
}