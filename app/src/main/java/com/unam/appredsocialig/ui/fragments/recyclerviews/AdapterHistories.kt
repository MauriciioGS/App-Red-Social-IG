package com.unam.appredsocialig.ui.fragments.recyclerviews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unam.appredsocialig.R
import com.unam.appredsocialig.data.PostApi

class AdapterHistories(private val historiesList: List<PostApi>, val context: Context): RecyclerView.Adapter<AdapterHistories.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var username: TextView
        var userPhoto: ImageView
        init{
            username = itemView.findViewById(R.id.tv_username)
            userPhoto = itemView.findViewById(R.id.iv_userPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = historiesList[position].name
        Glide.with(context)
            .load(historiesList[position].image)
            .circleCrop()
            .into(holder.userPhoto)
    }

    override fun getItemCount(): Int = historiesList.size

}