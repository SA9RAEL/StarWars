package com.example.starwarssearch.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarssearch.R
import com.example.starwarssearch.adapters.ItemsAdapter
import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.data.models.Planet
import com.example.starwarssearch.databinding.FragmentHomeBinding
import com.example.starwarssearch.presentation.ItemListPresentation
import com.example.starwarssearch.presentation.models.CharacterPresentation
import com.example.starwarssearch.presentation.models.PlanetPresentation
import com.example.starwarssearch.utils.hideKeyboard
import com.example.starwarssearch.viewmodels.CharactersViewModel
import com.google.android.material.internal.ViewUtils.hideKeyboard
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()

    private val itemAdapter: ItemsAdapter by lazy {
        ItemsAdapter(ItemsAdapter.OnClickListener { item ->
            val action: NavDirections? = when (item) {
                is CharacterPresentation ->
                    HomeFragmentDirections.actionHomeFragmentToCharactersDetailsFragment(item)

                is PlanetPresentation ->
                    HomeFragmentDirections.actionHomeFragmentToPlanetsDetailsFragment(item)

                else -> null
            }
            if (action != null) {
                findNavController().navigate(action)
            }
            binding.searchView.editText?.setText("")
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchView.setEndIconOnClickListener {
            setUpObserver(binding.searchView.editText?.text.toString())
            binding.charactersProgressBar.isVisible = true
            hideKeyboard()
        }
        setUpObserver("")
        setUpAdapter()
    }

    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters(searchString).collect { characters ->
                /* itemAdapter.submitData(lifecycle, characters) */
            }
        }
    }

    private fun setUpAdapter() {
        binding.charactersRecyclerview.adapter = itemAdapter

        itemAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (itemAdapter.snapshot().isEmpty()) {
                    binding.charactersProgressBar.isVisible = true
                }
                binding.textViewError.isVisible = false
            } else {
                binding.charactersProgressBar.isVisible = false

                val error = when {
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (itemAdapter.snapshot().isEmpty()) {
                        binding.textViewError.apply {
                            isVisible = true
                            text = it.error.localizedMessage
                        }
                    }
                }
            }

        }
    }

}