package com.test.networkandjson.adapter
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.networkandjson.R
import com.test.networkandjson.detail
import com.test.networkandjson.movie.Movie

class NowPlayingAdapter (private val layoutManager: GridLayoutManager? = null) : ListAdapter<Movie,NowPlayingAdapter.ViewHolder>(MovieDiffUtilCallback()) {
    companion object {
        const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.itemTitle)
        val tvDetail = itemView.findViewById<TextView>(R.id.itemDetail)
        val imgMovie = itemView.findViewById<ImageView>(R.id.itemImage)

        fun bind(item: Movie) {
            tvName.text = item.originalTitle
            tvDetail.text = item.overview
            Glide.with(itemView.context)
                .load(URL_POSTER + item.posterPath)
                .into(imgMovie)
            itemView.setOnClickListener {
               Log.e("aaa",item.toString())



            }
        }


    }

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
           return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)

    }



}

