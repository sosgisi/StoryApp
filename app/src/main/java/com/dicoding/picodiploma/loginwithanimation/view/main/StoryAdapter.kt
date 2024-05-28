package com.dicoding.picodiploma.loginwithanimation.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.api.Responses.ListStoryItem

class StoryAdapter(): ListAdapter<ListStoryItem, StoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick : ((ListStoryItem) -> Unit)? = null

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val rvImage: ImageView = itemView.findViewById(R.id.rvImage)
        val rvName: TextView = itemView.findViewById(R.id.rvName)
        val rvDescription: TextView = itemView.findViewById(R.id.rvDescription)
        val storyList : CardView = itemView.findViewById(R.id.story_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.story_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        Glide.with(holder.itemView.context)
            .load(currentItem.photoUrl)
            .into(holder.rvImage)
        holder.rvName.text = currentItem.name
        holder.rvDescription.text = currentItem.description.toString().replace("\"", "")
        holder.storyList.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>(){
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}