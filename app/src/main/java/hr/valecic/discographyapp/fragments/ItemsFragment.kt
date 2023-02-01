package hr.valecic.discographyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.valecic.discographyapp.adapter.ItemAdapter
import hr.valecic.discographyapp.databinding.FragmentItemsBinding
import hr.valecic.discographyapp.framework.fetchItems
import hr.valecic.discographyapp.model.Artist

class ItemsFragment : Fragment() {
    private lateinit var binding: FragmentItemsBinding
    private lateinit var artists: MutableList<Artist>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        artists = requireContext().fetchItems()
        binding = FragmentItemsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItems.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ItemAdapter(requireContext(), artists)
        }
    }
}