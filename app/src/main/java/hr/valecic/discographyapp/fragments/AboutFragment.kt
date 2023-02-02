package hr.valecic.discographyapp.fragments

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.valecic.discographyapp.databinding.FragmentAboutBinding
import java.time.LocalDate

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        setYearText()
        return binding.root
    }

    private fun setYearText() {
        val sb = StringBuilder()
        sb.append(binding.tvFooter.text).append(LocalDate.now().year)

        binding.tvFooter.text = sb
    }
}