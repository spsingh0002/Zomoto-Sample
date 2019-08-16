package som.sps.zmoto.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.listeners.SearchSelectionListener
import som.sps.zmoto.model.LocationSuggestion
import som.sps.zmoto.model.LocationSuggestionResponse

class BindingAdapters {


    companion object {

        @JvmStatic
        @BindingAdapter(value = arrayOf("app:searchData", "app:onClickListsner"),requireAll = true)
        fun bindSearchData(
            recycler: RecyclerView,
            data: LocationSuggestionResponse?,
            listener: SearchSelectionListener
        ) {
            data?.let {
                recycler.adapter = SearchAdapter(listener = listener, data = data.locationSuggestionList)
            }

        }
    }
}