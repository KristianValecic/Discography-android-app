package hr.valecic.discographyapp.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import hr.valecic.discographyapp.HostActivity
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.databinding.FragmentLoginBinding
import hr.valecic.discographyapp.databinding.FragmentLoginFavoritesBinding
import java.util.regex.Pattern


class LoginFavoritesFragment : Fragment() {
    private lateinit var binding: FragmentLoginFavoritesBinding
    private lateinit var tvFavorites: TextView
    private lateinit var btnLogin: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var progressBar: ProgressBar
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginFavoritesBinding.inflate(inflater, container, false)
        initAuth()
        initComponents()

        return binding.root
    }

    private fun initComponents() {
        tvFavorites = binding.tvFavorites
        btnLogin = binding.loginButton
        emailEditText = binding.emailEditText
        passwordEditText = binding.passwordEditText
        progressBar = binding.progressBar
        tvFavorites.setOnClickListener() {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuLgoinFavorites_to_menuFavorites)
//            Navigation.findNavController(HostActivity(), R.id.navController)
//                .navigate(R.id.action_menuLogin_to_menuRegister)
        }
        btnLogin.setOnClickListener() {
            loginUser()
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_menuLgoinFavorites_to_menuFavorites)
//            requireFragmentManager().popBackStack()
        }
    }

    private fun loginUser() {
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
        }

        auth.signInWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener() {
                if (it.isSuccessful) {
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(context, getString(R.string.logged_in), Toast.LENGTH_SHORT)
                        .show()
                    Navigation.findNavController(binding.root)
                        .navigate(R.id.action_menuLgoinFavorites_to_menuAccount)
                } else {
                    Toast.makeText(context, getString(R.string.loggin_error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }
}