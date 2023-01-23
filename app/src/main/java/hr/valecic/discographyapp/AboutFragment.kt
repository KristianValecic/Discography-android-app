package hr.valecic.discographyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hr.valecic.discographyapp.databinding.FragmentAboutBinding
import hr.valecic.discographyapp.databinding.FragmentItemsBinding
import org.w3c.dom.Text
import java.time.LocalDate
import java.util.zip.Inflater

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        setYearText(/*inflater.inflate(R.layout.fragment_about, container, false)*/)

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setYearText(/*view: View*/) {
        //val footer = view.findViewById<TextView>(R.id.tvFooter)
        val sb = StringBuilder()
        sb.append(binding.tvFooter.text).append(LocalDate.now().year)

        binding.tvFooter.text = sb
    }
}