package som.sps.zmoto.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import som.sps.zmoto.R
import som.sps.zmoto.listeners.SearchSelectionListener
import som.sps.zmoto.model.LocationSuggestionResponse
import som.sps.zmoto.model.Restaurant

class BindingAdapters {


    companion object {

        @JvmStatic
        @BindingAdapter(value = arrayOf("app:averageCost"), requireAll = true)
        fun bindAverageCost(textView: TextView, restaurant: Restaurant?) {
            textView.text =
                "Average ${restaurant?.averageCostForTwo}${restaurant?.currency} for ${restaurant?.priceRange}"

        }

        @JvmStatic
        @BindingAdapter("app:address")
        fun bindAddress(textView: TextView, restaurant: Restaurant?) {
            textView.text =
                "${restaurant?.location?.address}\n${restaurant?.location?.locality}\n${restaurant?.location?.city}"

        }

        @JvmStatic
        @BindingAdapter(" app:highlight")
        fun bindHighlightsText(
            textView: TextView,
            highlights: List<String>?
        ) {
            textView.text ="\nHighLights: \n  ${ highlights?.joinToString(",")}"

        }


        @JvmStatic
        @BindingAdapter(" app:highlight")
        fun bindHighlights(
            chipGroup: com.google.android.material.chip.ChipGroup,
            highlights: List<String>?
        ) {
            chipGroup.removeAllViews()
            highlights?.forEach { item ->
                val chip = Chip(chipGroup.context).apply {
                    setTextAppearanceResource(R.style.Widget_MaterialComponents_Chip_Entry);
                    text = item
                    isCheckable = false
                    isClickable = false
                    isFocusable = true
                    textSize = context.resources.getDimension(R.dimen.chip_text_size)
                }
                chipGroup.addView(chip)

            }

        }


        @JvmStatic
        @BindingAdapter(value = arrayOf("app:searchData", "app:onClickListsner"), requireAll = true)
        fun bindSearchData(
            recycler: RecyclerView,
            data: LocationSuggestionResponse?,
            listener: SearchSelectionListener
        ) {
            data?.let {
                recycler.adapter =
                    SearchAdapter(listener = listener, data = data.locationSuggestionList)
            }

        }

        @JvmStatic
        @BindingAdapter(value = arrayOf("app:url"), requireAll = true)
        fun bindImageView(imageView: ImageView, url: String?): Unit {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}