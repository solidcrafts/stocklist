package io.solidcrafts.stocklist.ui.listings_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.solidcrafts.stocklist.domain.Data
import io.solidcrafts.stocklist.domain.Listing
import io.solidcrafts.stocklist.repository.ListingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingsFragmentViewModel @Inject constructor(
    private val repository: ListingsRepository
) : ViewModel() {

    init {
        updateListings(false, "")
    }

    private var searchJob: Job? = null

    private val _navigateToListingDetails: MutableLiveData<Listing> = MutableLiveData()
    val navigateToListingDetails: LiveData<Listing> = _navigateToListingDetails

    private val _listings: MutableLiveData<List<Listing>> = MutableLiveData(listOf())
    val listings: LiveData<List<Listing>> = _listings

    fun handleEvent(event: ListingsFragmentEvent) {
        when (event) {
            is ListingsFragmentEvent.OnListingClicked -> navigateToListingDetails(event.listing)
            is ListingsFragmentEvent.OnSearchQueryChanged -> onSearchQueryChanged(event.query)
            ListingsFragmentEvent.Refresh -> TODO()
            ListingsFragmentEvent.ListingNavigationCompleted -> navigateToListingDetailsCompleted()
        }
    }

    private fun updateListings(fetchRemote: Boolean, query: String) {
        viewModelScope.launch {
            repository.getListings(fetchRemote, query).collect {
                when (it) {
                    is Data.Error -> {

                    }
                    is Data.Loading -> {

                    }
                    is Data.Success -> {
                        it.data?.let { updatedList ->
                            _listings.postValue(updatedList)
                        }
                    }
                }
            }
        }
    }

    private fun navigateToListingDetails(listing: Listing) {
        _navigateToListingDetails.value = listing
    }

    private fun navigateToListingDetailsCompleted() {
        _navigateToListingDetails.value = null
    }

    private fun onSearchQueryChanged(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            updateListings(query = query, fetchRemote = false)
        }
    }
}