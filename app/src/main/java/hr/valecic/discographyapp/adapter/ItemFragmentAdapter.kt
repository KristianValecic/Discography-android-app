package hr.valecic.discographyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.valecic.discographyapp.*
import hr.valecic.discographyapp.model.Artist

class ItemFragmentAdapter(private val context: Context, /*private val artist: Artist*/private val artists: MutableList<Artist>) :
    RecyclerView.Adapter<ItemFragmentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        private val ivItem = itemView.findViewById<ImageView>(R.id.ivBand)
        private val tvArtistName = itemView.findViewById<TextView>(R.id.tvArtistName)
        private val tvListeners = itemView.findViewById<TextView>(R.id.tvListeners)
        private val tvPlaycount = itemView.findViewById<TextView>(R.id.tvPlayercount)
        private val tvBio = itemView.findViewById<TextView>(R.id.tvBio)
//        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)

        fun bind(artist: Artist) {
            //Picasso.get()
            //.load(File(item.picturePath))
            //.error(R.drawable.nasa)
            //.transform(RoundedCornersTransformation(50, 5))
            //.into(ivItem)
            tvArtistName.text = artist.name
            tvListeners.text = "${tvListeners.text}  ${artist.listeners}"
            tvPlaycount.text = "${tvPlaycount.text}  ${artist.playcount}"
            tvBio.text = "${tvBio.text}  ${artist.bio}"
            //            tvAdditionalInfo.text = "${R.string.artist_match} ${artist.match}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = artists[position]
        ArtistService.enqueueGetArtistInfo(context, artist.name)
//        holder.itemView.setOnClickListener {
////            update favorite 1:18
//            item.favorite = !item.favorite
//
//            context.contentResolver.update(
//                ContentUris.withAppendedId(DISCOG_PROVIDER_CONTENT_URI, item._id!!),
//                ContentValues().apply {
//                    put(item::favorite.name, item.favorite)
//                },
//                null,
//                null
//            )
//            notifyItemChanged(position)
//        }
        holder.bind(artist)
    }

//    private fun deleteItem(position: Int) {
//        val item = artists[position]
//        context.contentResolver.delete(
//            ContentUris.withAppendedId(DISCOG_PROVIDER_CONTENT_URI, item._id!!),
//            null,
//            null
//        )
////            File(item.picturePath).delete()
//        artists.removeAt(position)
//        Toast.makeText(context, context.getString(R.string.deleted_item), Toast.LENGTH_SHORT).show()
//        notifyDataSetChanged()
//    }

    override fun getItemCount() = artists.size
}