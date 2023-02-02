package hr.valecic.discographyapp.adapter

import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hr.valecic.discographyapp.DISCOG_PROVIDER_CONTENT_URI
import hr.valecic.discographyapp.LoadActivity
import hr.valecic.discographyapp.POSITION
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.framework.startActivityNoHistory
import hr.valecic.discographyapp.model.Artist
import java.lang.String
import kotlin.Int


class ItemAdapter(private val context: Context, private val items: MutableList<Artist>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivBand)
        private val tvBandName = itemView.findViewById<TextView>(R.id.tvBandName)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)

        fun bind(artist: Artist) {
            tvBandName.text = artist.name
            tvAdditionalInfo.text = "${itemView.context.getString(R.string.artist_match)} ${artist.match}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete artist")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                    deleteItem(position)
        }
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()

            true
        }
        holder.itemView.setOnClickListener {
            context.startActivityNoHistory<LoadActivity>(POSITION, item.name)
            }
        holder.bind(item)
    }

    private fun deleteItem(position: Int) {
        val item = items[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(DISCOG_PROVIDER_CONTENT_URI, item._id!!),
            null,
            null
        )
        items.removeAt(position)
        Toast.makeText(context, context.getString(R.string.deleted_item), Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}