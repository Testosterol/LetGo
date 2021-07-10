package com.testosterolapp.letgoproject.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class GenericViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    abstract fun onBindView(position: Int)
}