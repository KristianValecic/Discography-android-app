package hr.valecic.discographyapp

import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.valecic.discographyapp.model.Artist
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

const val ADDITIONAL_INFO_TEXT = "Match to searched band = "

class ItemAdapter(private val context: Context, private val items: MutableList<Artist>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

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
                tvAdditionalInfo.text = ADDITIONAL_INFO_TEXT + artist.match
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           // val item = items[position]

//            holder.itemView.setOnLongClickListener {
//                deleteItem(position)
//                true
//            }
//            holder.itemView.setOnClickListener {
//                context.startActivity<ItemPagerActivity>(POSITION, position)
//            }
//
            //holder.bind(item)
            holder.bind(items[position])
        }

//        private fun deleteItem(position: Int) {
//            val item = items[position]
//            context.contentResolver.delete(
//                ContentUris.withAppendedId(DISCOG_PROVIDER_CONTENT_URI, item._id!!),
//                null,
//                null
//            )
//            File(item.picturePath).delete()
//            items.removeAt(position)
//            notifyDataSetChanged()
//        }

        override fun getItemCount() = items.size
}