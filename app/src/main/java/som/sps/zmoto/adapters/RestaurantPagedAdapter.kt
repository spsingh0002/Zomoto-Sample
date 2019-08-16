package som.sps.zmoto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.R
import som.sps.zmoto.databinding.LayoutRestaurantItemBinding
import som.sps.zmoto.model.Restaurants

class RestaurantPagedAdapter :
    PagedListAdapter<Restaurants, RestaurantPagedAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<Restaurants>() {
        override fun areItemsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem.restaurant.id == newItem.restaurant.id
        }

    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate<LayoutRestaurantItemBinding>(
                LayoutInflater.from(
                    parent.context
                ), R.layout.layout_restaurant_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.restaurantItemBinding.restaurant = getItem(position)
        holder.restaurantItemBinding.executePendingBindings()
    }


    class ViewHolder(val restaurantItemBinding: LayoutRestaurantItemBinding) :
        RecyclerView.ViewHolder(restaurantItemBinding.root) {

    }
}