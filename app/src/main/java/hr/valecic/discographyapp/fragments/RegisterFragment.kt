package hr.valecic.discographyapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import hr.valecic.discographyapp.HostActivity
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    lateinit var auth: FirebaseAuth
    private lateinit var btnRegister: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var tvLogin: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initAuth()
        initComponents()
        return binding.root
    }


    private fun initComponents() {
        tvLogin = binding.tvLogin
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText
        btnRegister = binding.registerButton
        btnRegister.setOnClickListener() {
            createUser()
        }
        tvLogin.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuRegister_to_menuLogin)

        }
    }

    private fun createUser() {
        var email = emailEditText.text
        var password = passwordEditText.text

        if (TextUtils.isEmpty(email)) {
            emailEditText.error = "Email cannot be empty"
            emailEditText.requestFocus()
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Please enter valid email"
            emailEditText.requestFocus()
            return
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.error = "Password cannot be empty"
            passwordEditText.requestFocus()
            return
        } else if (password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters long"
            passwordEditText.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            context,
                            getString(R.string.account_created),
                            Toast.LENGTH_SHORT
                        ).show()
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_menuRegister_to_menuAccount)
                    } else {
                        Toast.makeText(context, getString(R.string.reg_error), Toast.LENGTH_SHORT)
                            .show()
                    }
                })
    }


    private fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }
}