package io.solidcrafts.stocklist.ui.listings_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.solidcrafts.stocklist.databinding.ListingItemBinding
import io.solidcrafts.stocklist.domain.Listing

class ListingsAdapter(private val clickListener: ListingClickListener) : ListAdapter<Listing, ListingItemViewHolder>(ListingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListingItemBinding.inflate(inflater, parent, false)
        return ListingItemViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ListingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ListingItemViewHolder(
    private val binding: ListingItemBinding,
    private val clickListener: ListingClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Listing) {
        binding.clickListener = clickListener
        binding.listing = item
        binding.executePendingBindings()
    }
}

class ListingDiffCallback : DiffUtil.ItemCallback<Listing>() {
    override fun areItemsTheSame(oldItem: Listing, newItem: Listing): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Listing, newItem: Listing): Boolean {
        return oldItem.symbol == newItem.symbol
    }
}

class ListingClickListener(val listener: (listing: Listing) -> Unit) {
    fun onClick(listing: Listing) = listener(listing)
}