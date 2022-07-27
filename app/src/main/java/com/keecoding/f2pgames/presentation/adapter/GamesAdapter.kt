package com.keecoding.f2pgames.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keecoding.f2pgames.R
import com.keecoding.f2pgames.data.db.model.GameModel
import com.keecoding.f2pgames.databinding.ItemGameLayoutBinding

class GamesAdapter(
    private val onClick: (Int, ImageView) -> Unit
): RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<GameModel>() {
        override fun areItemsTheSame(oldItem: GameModel, newItem: GameModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameModel, newItem: GameModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    inner class ViewHolder(private val binding: ItemGameLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(gameModel: GameModel) {
            Glide.with(binding.root.context).load(gameModel.thumbnail).into(binding.imageView)
            binding.tvGameName.text = gameModel.title
            binding.tvReleaseDate.text = gameModel.releaseDate
            binding.tvPublisher.text = gameModel.publisher
            binding.tvShortDesc.text = gameModel.shortDescription
            when (gameModel.platform) {
                "PC (Windows)" -> binding.ivPlatform.setImageResource(R.drawable.ic_baseline_computer_24)
                "Web Browser" -> binding.ivPlatform.setImageResource(R.drawable.ic_baseline_web_24)
            }

            binding.root.setOnClickListener { onClick(gameModel.id, binding.imageView) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = differ.currentList[position]
        holder.bind(game)
    }

    override fun getItemCount() = differ.currentList.size
}