package som.sps.zmoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import som.sps.zmoto.databinding.RestaurantDetailActivityBinding
import som.sps.zmoto.ui.restaurantdetail.RestaurantDetailFragment

class RestaurantDetail : AppCompatActivity() {

    lateinit var binding: RestaurantDetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.restaurant_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RestaurantDetailFragment.newInstance(intent.extras))
                .commitNow()
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
