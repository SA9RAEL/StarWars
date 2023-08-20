package com.example.starwarssearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarssearch.data.models.FilmsResponse
import com.example.starwarssearch.databinding.FilmRowBinding

class FilmsAdapter : ListAdapter<FilmsResponse, FilmsAdapter.FilmViewHolder>(FILMS_COMPARATOR) {

    inner class FilmViewHolder(private val binding: FilmRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filmsResponse: FilmsResponse?) {
            with(binding) {
                textViewFilmName.text = filmsResponse?.title
                textViewOpeningCrawl.text = filmsResponse?.openingCrawl
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsAdapter.FilmViewHolder =
        FilmViewHolder(FilmRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FilmsAdapter.FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val FILMS_COMPARATOR = object : DiffUtil.ItemCallback<FilmsResponse>() {
            override fun areItemsTheSame(oldItem: FilmsResponse, newItem: FilmsResponse): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: FilmsResponse,
                newItem: FilmsResponse
            ): Boolean = oldItem == newItem
        }
    }
}