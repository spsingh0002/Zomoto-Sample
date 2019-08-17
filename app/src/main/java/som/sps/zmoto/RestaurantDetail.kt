package som.sps.zmoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import som.sps.zmoto.databinding.RestaurantDetailActivityBinding
import som.sps.zmoto.ui.restaurantdetail.RestaurantDetailFragment
import som.sps.zmoto.viewmodel.RestaurantDetailViewModel
import timber.log.Timber

class RestaurantDetail : AppCompatActivity() {

    lateinit var binding: RestaurantDetailActivityBinding

    private lateinit var viewModel: RestaurantDetailViewModel
    private var resId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.restaurant_detail_activity)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        resId = intent?.getIntExtra("ResId", -1) ?: -1
        viewModel = ViewModelProviders.of(this).get(RestaurantDetailViewModel::class.java)
        viewModel.fetchRestaurantDetail(resId)
        binding.model=viewModel
        binding.lifecycleOwner=this

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
