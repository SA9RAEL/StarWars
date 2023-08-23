package com.example.starwarssearch.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarssearch.databinding.ItemRowBinding
import com.example.starwarssearch.presentation.models.CharacterPresentation

class CharactersViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {

    private val binding: ItemRowBinding by viewBinding(ItemRowBinding::bind)
    fun bind(character: CharacterPresentation?) {
        binding.nameTextView.text = character?.name
    }

}