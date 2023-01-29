package hr.valecic.discographyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.model.Artist

class TagAdapter(private val items: MutableList<String>): RecyclerView.Adapter<TagAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTag = itemView.findViewById<TextView>(R.id.tvTag)

        fun bind(text: String){
            tvTag.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.ViewHolder {
        return TagAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tag, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TagAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

}