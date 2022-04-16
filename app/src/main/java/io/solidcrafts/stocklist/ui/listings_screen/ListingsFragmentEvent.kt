package io.solidcrafts.stocklist.ui.listings_screen

import io.solidcrafts.stocklist.domain.Listing

sealed class ListingsFragmentEvent {
    object Refresh: ListingsFragmentEvent()
    object ListingNavigationCompleted: ListingsFragmentEvent()
    data class OnSearchQueryChanged(val query: String): ListingsFragmentEvent()
    data class OnListingClicked(val listing: Listing): ListingsFragmentEvent()
}
