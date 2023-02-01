package hr.valecic.discographyapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.adapter.ItemAdapter
import hr.valecic.discographyapp.databinding.FragmentFavoritesBinding
import hr.valecic.discographyapp.framework.fetchItems
import hr.valecic.discographyapp.model.Artist


class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var btnLogin: Button
    private lateinit var clNotLoggedIn: ConstraintLayout
    private lateinit var rvFavoritedItems: RecyclerView
    private lateinit var favorites: MutableList<Artist>
    lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        initComponents()
        initAuth()

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }

    private fun initAuth() {
        auth = FirebaseAuth.getInstance()
    }

    private fun initComponents() {
        btnLogin = binding.btnLogin
        clNotLoggedIn = binding.clNotLoggedIn
        rvFavoritedItems= binding.rvFavoritedItems
        btnLogin.setOnClickListener(){
            Navigation.findNavController(binding.root).navigate(R.id.action_menuFavorites_to_menuLgoinFavorites)
        }
    }

    override fun onStart() {
        super.onStart()
//        if user is logged in show favorites list
        if(auth.currentUser != null){
            loadFavorites()
            clNotLoggedIn.visibility = View.GONE
            rvFavoritedItems.visibility = View.VISIBLE

        }
    }

    private fun loadFavorites() {
        favorites = requireContext().fetchItems().filter{ it.favorite } as MutableList<Artist>
        binding.rvFavoritedItems.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ItemAdapter(requireContext(), favorites)
        }
    }
}