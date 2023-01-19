package hr.valecic.discographyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text
import java.time.LocalDate
import java.util.zip.Inflater

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setYearText(inflater.inflate(R.layout.fragment_about, container, false))
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    private fun setYearText(view: View) {
        val footer = view.findViewById<TextView>(R.id.tvFooter)
        val sb = StringBuilder()
        sb.append(footer.text).append(LocalDate.now().year)
        footer.text = sb
    }
}