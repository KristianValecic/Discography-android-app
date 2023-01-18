package hr.valecic.discographyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setYearText()
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    private fun setYearText() {
        val footer = R.id.tvFooter
        footer.
    }
}