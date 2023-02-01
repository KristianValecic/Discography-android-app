package hr.valecic.discographyapp.adapter

import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import hr.valecic.discographyapp.*
import hr.valecic.discographyapp.framework.startActivity
import hr.valecic.discographyapp.framework.startActivityNoHistory
import hr.valecic.discographyapp.model.Artist
import androidx.appcompat.app.AppCompatActivity as AppCompatActivit


class ItemAdapter(private val context: Context, private val items: MutableList<Artist>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivBand)
        private val tvBandName = itemView.findViewById<TextView>(R.id.tvBandName)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)

        fun bind(artist: Artist) {
            //Picasso.get()
            //.load(File(item.picturePath))
            //.error(R.drawable.nasa)
            //.transform(RoundedCornersTransformation(50, 5))
            //.into(ivItem)
            tvBandName.text = artist.name
//            val sb = StringBuilder()
//            sb.append(R.string.artist_match).append(" ").append(artist.match)
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
                .setMessage("Are you sure you want to delete this entry?") // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                    deleteItem(position)
                } // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()

            true
        }
        holder.itemView.setOnClickListener {
            context.startActivityNoHistory<LoadActivity>(POSITION, position)
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
//            File(item.picturePath).delete()
        items.removeAt(position)
        Toast.makeText(context, context.getString(R.string.deleted_item), Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}