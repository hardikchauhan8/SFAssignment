package com.example.sfassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sfassignment.databinding.RowFilmListItemBinding
import com.example.sfassignment.model.CharacterFilmResponse

class CharacterFilmListAdapter(
    private var filmList: List<CharacterFilmResponse?>?
) : RecyclerView.Adapter<CharacterFilmListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RowFilmListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        filmList?.let {
            val item = it[position]
            holder.tvFilmTitle.text = item?.title
            holder.tvOpeningCrawl.text = item?.openingCrawl
        }
    }

    override fun getItemCount(): Int = filmList?.size ?: 0
    fun loadFilmsData(it: List<CharacterFilmResponse?>) {
        filmList = it
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: RowFilmListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvFilmTitle: TextView = binding.tvFilmTitle
        val tvOpeningCrawl: TextView = binding.tvOpeningCrawl
    }
}

