package com.example.starwarssearch.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwarssearch.R
import com.example.starwarssearch.adapters.ItemsAdapter
import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.data.models.Planet
import com.example.starwarssearch.databinding.FragmentHomeBinding
import com.example.starwarssearch.presentation.models.CharacterPresentation
import com.example.starwarssearch.presentation.models.PlanetPresentation
import com.example.starwarssearch.viewmodels.CharactersViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()

    private val itemAdapter: ItemsAdapter by lazy {
        ItemsAdapter(ItemsAdapter.OnClickListener { item ->
            val action: NavDirections = when (item) {
                is CharacterPresentation ->
                    HomeFragmentDirections.actionHomeFragmentToCharactersDetailsFragment(item)

                is PlanetPresentation ->
                    HomeFragmentDirections.actionHomeFragmentToPlanetsDetailsFragment(item)

                else -> { throw IllegalStateException("Unknown Directions") }
            }
            findNavController().navigate(action)
            binding.searchView.editText?.setText("")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.searchView.setEndIconOnClickListener {
            setUpObserver()
        }
    }

    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCharacters(searchString).collect {
                itemAdapter.submitData(lifecycle, it)
            }
        }
    }

}