package com.example.starwarssearch.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarssearch.R
import com.example.starwarssearch.adapters.viewholders.CharactersViewHolder
import com.example.starwarssearch.adapters.viewholders.PlanetsViewHolder
import com.example.starwarssearch.presentation.ItemListPresentation
import com.example.starwarssearch.presentation.models.CharacterPresentation
import com.example.starwarssearch.presentation.models.PlanetPresentation

class ItemsAdapter() :
    PagingDataAdapter<ItemListPresentation, RecyclerView.ViewHolder>(ITEMS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CHARACTER -> CharactersViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
            )

            VIEW_TYPE_PLANET -> PlanetsViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
            )

            else -> throw IllegalArgumentException("Invalid View Holder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is CharactersViewHolder -> holder.bind(getItem(position) as CharacterPresentation)
            is PlanetsViewHolder -> holder.bind(getItem(position) as PlanetPresentation)
            else -> {}
        }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is CharacterPresentation -> VIEW_TYPE_CHARACTER
        else -> VIEW_TYPE_PLANET
    }

    companion object {
        private const val VIEW_TYPE_CHARACTER = 0
        private const val VIEW_TYPE_PLANET = 1

        private val ITEMS_COMPARATOR = object : DiffUtil.ItemCallback<ItemListPresentation>() {
            override fun areItemsTheSame(
                oldItem: ItemListPresentation,
                newItem: ItemListPresentation
            ): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ItemListPresentation,
                newItem: ItemListPresentation
            ): Boolean = oldItem == newItem
        }
    }
}