package com.example.authors.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.authors.R
import com.example.authors.data.network.AdItem
import com.example.authors.data.network.BaseAdapterItem

class AuthorsListAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<AuthorsListAdapter.ViewHolder>() {
    lateinit var authors: ArrayList<BaseAdapterItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        onClickListener.onLoadMore(position)
        if (authors[position] is AuthorModel) {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(authors[position] as AuthorModel)
            }
            val authorsList = authors[position] as AuthorModel
            Glide.with(context)
                .asBitmap()
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .placeholder(R.drawable.avd_loading_anim)
                .load(authorsList.authorDownloadUrl)
                .into(holder.authorImage)
            holder.authorName.text = authorsList.authorName.toString()
        } else if (authors[position] is AdItem) {
            Glide.with(context)
                .asBitmap()
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .placeholder(R.drawable.avd_loading_anim)
                .load((authors[position] as AdItem).adImage)
                .into(holder.authorImage)
        }
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    class ViewHolder(authorItem: View) : RecyclerView.ViewHolder(authorItem) {
        val authorImage: ImageView = authorItem.findViewById(R.id.item_author_image)
        val authorName: TextView = authorItem.findViewById(R.id.item_author_name)
    }

    fun submitList(_authors: ArrayList<BaseAdapterItem>) {
        authors.addAll(_authors)
        notifyDataSetChanged()
    }
}

interface OnClickListener {
    fun onClick(authorModel: AuthorModel)
    fun onLoadMore(position: Int)
}
