package hr.valecic.discographyapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.databinding.FragmentAboutBinding
import hr.valecic.discographyapp.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private lateinit var tvEmail: TextView
    private lateinit var logoutButton: Button
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        initAuth()
        initComponents()
        return binding.root
    }

    private fun initComponents() {
        tvEmail = binding.tvEmail
        logoutButton = binding.logoutButton
        logoutButton.setOnClickListener(){
            AlertDialog.Builder(context)
                .setTitle("Logout?")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                    auth.signOut()
                    Navigation.findNavController(binding.root).navigate(R.id.action_menuAccount_to_menuLoggedInAccount)
                }
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()
        }
    }

    private fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        var user =  auth.currentUser
//        if user isn't logged in moves to login fragment
        if(user == null){
            Navigation.findNavController(binding.root).navigate(R.id.action_menuAccount_to_menuLoggedInAccount)
        }else{
            tvEmail.text = user.email
        }
    }

}