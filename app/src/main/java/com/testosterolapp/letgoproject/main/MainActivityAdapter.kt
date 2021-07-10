package com.testosterolapp.letgoproject.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.testosterolapp.letgoproject.R
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.util.GenericViewHolder
import com.testosterolapp.letgoproject.util.QuickContactDivot

class MainActivityAdapter : PagedListAdapter<Character, GenericViewHolder>(
    DiffUtilCallBack()
) {

    private var character: Character? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val contactView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent, false
        )
        return MyViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.onBindView(position)
    }

    inner class MyViewHolder(itemView: View) : GenericViewHolder(itemView) {
        private var mName: TextView = itemView.findViewById(R.id.name)
        private var mDescription: TextView = itemView.findViewById(R.id.description)
        var mAvatarQuickContactDivot: QuickContactDivot? = itemView.findViewById(R.id.avatar)

        override fun onBindView(position: Int) {
            if (position <= -1) {
                return
            }
            character = try {
                getItem(position)
            } catch (e: IndexOutOfBoundsException) {
                return
            }

            mName.text = character!!.name
            mDescription.text = character!!.description

            Glide.with(itemView)
                .load(character!!.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_contact_picture)
                .into(mAvatarQuickContactDivot!!)

        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id_fk_superhero == newItem.id_fk_superhero
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

    }

}