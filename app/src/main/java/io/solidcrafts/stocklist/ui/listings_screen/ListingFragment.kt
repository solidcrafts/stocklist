package io.solidcrafts.stocklist.ui.listings_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.solidcrafts.stocklist.databinding.FragmentListingBinding
import io.solidcrafts.stocklist.ui.listings_screen.ListingsFragmentEvent.*

@AndroidEntryPoint
class ListingFragment : Fragment() {
    private val listingsFragmentViewModel: ListingsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListingBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = listingsFragmentViewModel

        binding.recycler.adapter = ListingsAdapter(ListingClickListener {

        })

        listingsFragmentViewModel.listings.observe(viewLifecycleOwner) {
            (binding.recycler.adapter as ListingsAdapter).submitList(it)
        }

        listingsFragmentViewModel.navigateToListingDetails.observe(viewLifecycleOwner) {
            // navigate by navigation library
            listingsFragmentViewModel.handleEvent(ListingNavigationCompleted)
        }

        return binding.root
    }
}