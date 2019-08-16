package som.sps.zmoto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.R
import som.sps.zmoto.databinding.LayoutSearchItemBinding
import som.sps.zmoto.listeners.SearchSelectionListener
import som.sps.zmoto.model.LocationSuggestion

class SearchAdapter(
    private val listener: SearchSelectionListener,
    private val data: List<LocationSuggestion>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutSearchItemBinding = DataBindingUtil.inflate<LayoutSearchItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_search_item,
            parent,
            false
        )
        return ViewHolder(layoutSearchItemBinding, listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data.get(position))
    }


    class ViewHolder(
        private val itemBinding: LayoutSearchItemBinding,
        private val listener: SearchSelectionListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(suggestion: LocationSuggestion) {
            itemBinding.listener = listener
            itemBinding.location = suggestion
            itemBinding.executePendingBindings()

        }

    }
}