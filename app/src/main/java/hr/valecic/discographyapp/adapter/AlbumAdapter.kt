package hr.valecic.discographyapp.adapter


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


class AlbumAdapter( private val albums: MutableList<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivAlbum = itemView.findViewById<ImageView>(R.id.ivAlbum)
        private val tvAlbum = itemView.findViewById<TextView>(R.id.tvAlbum)

        fun bind(album: Album) {
                if (album.images?.isEmpty() != true) {
                    Glide.with(itemView.context)
                        .load(album.images?.get(album.images!!.size - 1)?.text)
                        .into(ivAlbum)
                }
                tvAlbum.text = album.name ?: ""
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