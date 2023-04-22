package com.example.myapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemCatBinding
import com.example.myapplication.domain.Cat

class CatsAdapter : RecyclerView.Adapter<CatsAdapter.CatsViewHolder>() {

    private var cats = emptyList<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val binding = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatsViewHolder(binding)
    }

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(cats[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(cats: List<Cat>) {
        this.cats = cats
        notifyDataSetChanged()
    }

    class CatsViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            binding.breed.text = cat.breedName.orEmpty()
            binding.origin.text = cat.origin.orEmpty()
            Glide
                .with(binding.root.context)
                .load("https://cdn2.thecatapi.com/images/${cat.referenceId}.jpg")
                .centerCrop()
                .into(binding.image)
        }
    }
}
