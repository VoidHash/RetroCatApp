package com.voidhash.thecatapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.thecatapp.R
import com.voidhash.thecatapp.models.Kitty
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerAdapter(private var items: List<Kitty>, private val context: Context)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder?.let {
            it.bindView(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setListItems(newItems: List<Kitty>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Kitty) {
            val name = itemView.name
            name.text = item.name
        }
    }
}