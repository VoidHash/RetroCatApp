package com.voidhash.thecatapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.thecatapp.R
import com.voidhash.thecatapp.backend.models.Kitty
import com.voidhash.thecatapp.databinding.ItemViewBinding
import com.voidhash.thecatapp.ui.listeners.CatClickListener

class RecyclerAdapter(private var items: List<Kitty>, private val context: Context)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    lateinit var myClickListener: CatClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.name.text = this.name
                binding.itemLayout.setOnClickListener {
                    myClickListener.onCatClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListItems(newItems: List<Kitty>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}