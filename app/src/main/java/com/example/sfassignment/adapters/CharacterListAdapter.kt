package com.example.sfassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sfassignment.R
import com.example.sfassignment.databinding.RowCharacterListItemBinding
import com.example.sfassignment.model.CharacterDetailResponse
import com.example.sfassignment.utils.ApiConstants
import com.example.sfassignment.utils.alien
import com.example.sfassignment.utils.female
import com.example.sfassignment.utils.male

class CharacterListAdapter(
    private var characterList: List<CharacterDetailResponse?>?
) : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RowCharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        characterList?.let {
            val item = it[position]
            holder.tvTitle.text = item?.name
            when (item?.gender) {
                ApiConstants.GENDER_MALE -> holder.tvGender.male()
                ApiConstants.GENDER_FEMALE -> holder.tvGender.female()
                else -> holder.tvGender.alien()
            }
            holder.itemView.setOnClickListener {
                val bundle = bundleOf("characterDetails" to item)

                holder.itemView.findNavController()
                    .navigate(R.id.action_characterlistfragment_to_characterdetailFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int = characterList?.size ?: 0

    fun loadCharacters(it: List<CharacterDetailResponse?>?) {
        characterList = it
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: RowCharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle: TextView = binding.tvTitle
        val tvGender: TextView = binding.tvGender
    }

}

