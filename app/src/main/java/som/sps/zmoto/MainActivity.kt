package som.sps.zmoto

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import som.sps.zmoto.databinding.ActivityMainBinding
import som.sps.zmoto.listeners.SearchSelectionListener
import som.sps.zmoto.model.LocationSuggestion
import som.sps.zmoto.network.Constants
import som.sps.zmoto.ui.main.SectionsPagerAdapter
import som.sps.zmoto.viewmodel.CategoryViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(), SearchSelectionListener {

    private var searchLocationFragment: SearchLocationFragment? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        binding.toolbar.subtitle =PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.KEY_LOACTION_TITLE,Constants.LOCATION)
        setSupportActionBar(binding.toolbar)
        binding.categoryViewModel = categoryViewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        addObservers()
        categoryViewModel.fetchCategories()

    }


    private fun addObservers() {
        categoryViewModel.categories.observe(this, Observer {
            it?.let {
                val sectionsPagerAdapter = SectionsPagerAdapter(
                    this, supportFragmentManager,
                    it
                )
                binding.viewPager.adapter = sectionsPagerAdapter
                binding.tabs.setupWithViewPager(binding.viewPager)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions_home, menu)
        val searchItem = menu?.findItem(R.id.action_search)

        var searchView:SearchView? = searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView?.queryHint="Search Location"

        searchView?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Timber.i("SearchOnQueryTextSubmit: $query")
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                searchItem.collapseActionView()
                searchLocationFragment = SearchLocationFragment.newInstance(query)
                searchLocationFragment?.show(supportFragmentManager, "location")
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                Timber.i("onQueryTextChange: $s")
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }



    override fun onSelection(location: LocationSuggestion) {
        Timber.i("location: $location")
        val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
        editor.putString(Constants.KEY_LOCATION, location.cityName);
        editor.putFloat(Constants.KEY_LAT, location.latitude.toFloat());
        editor.putFloat(Constants.KEY_LON, location.longitude.toFloat());
        editor.putString(Constants.KEY_LOACTION_TITLE, location.title);
        editor.putInt(Constants.KEY_ENTITY_TYPE, location.entityId);
        editor.putString(Constants.KEY_ENTITY_TYPE, location.entityType);
        editor.apply()
        binding.toolbar.subtitle = "${location.title}"
        searchLocationFragment?.dismiss()
        searchLocationFragment=null
    }

}