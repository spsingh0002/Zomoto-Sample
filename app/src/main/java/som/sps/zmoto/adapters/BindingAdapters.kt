package som.sps.zmoto.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

        @JvmStatic
        @BindingAdapter(value = arrayOf("app:url"),requireAll = true)
        fun bindImageView( imageView: ImageView,url: String): Unit {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}