package hr.valecic.discographyapp.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.model.Album
import java.io.InputStream
import java.net.URL
import javax.sql.DataSource


class AlbumAdapter(/*context: Context,*/ private val albums: MutableList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    class ViewHolder(/*context: Context,*/ itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivAlbum = itemView.findViewById<ImageView>(R.id.ivAlbum)
        private val tvAlbum = itemView.findViewById<TextView>(R.id.tvAlbum)

        fun bind(album: Album) {
                //            Picasso.get()
//            .load(File(item.picturePath))
//            .error(R.drawable.nasa)
////            .transform(RoundedCornersTransformation(50, 5))
//            .into(ivAlbum)

                if (album.images?.isEmpty() != true) {
                    Glide.with(itemView.context)
                        .load(album.images?.get(album.images!!.size - 1)?.text)
//                .listener(requestListener)
//                    .error(R.drawable.band_default_mg)
                        .into(ivAlbum)
                }
                tvAlbum.text = album.name ?: ""
        }

        fun loadImageFromWebOperations(url: String?): Drawable? {
            return try {
                val input: InputStream = URL(url).getContent() as InputStream
                Drawable.createFromStream(input, "src name")
            } catch (e: Exception) {
                null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumAdapter.ViewHolder {
        return AlbumAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_album, parent, false)
        )
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val item = albums[position]
        holder.bind(item)
    }

}