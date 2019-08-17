package som.sps.zmoto.ui.restaurantdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import som.sps.zmoto.R
import som.sps.zmoto.viewmodel.RestaurantDetailViewModel
import timber.log.Timber

class RestaurantDetailFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle): RestaurantDetailFragment {

            val detailFragment = RestaurantDetailFragment()
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    private lateinit var viewModel: RestaurantDetailViewModel
    private var resId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resId = arguments?.getInt("ResId", -1) ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.restaurant_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RestaurantDetailViewModel::class.java)

        viewModel.restaurant.observe(this, Observer {
            Timber.i("$it")
        })

        viewModel.isLoading.observe(this, Observer {
            Timber.i("isLoading ->$it")
        })

        viewModel.fetchRestaurantDetail(resId)
    }

}
