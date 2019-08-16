package som.sps.zmoto.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.R
import som.sps.zmoto.adapters.RestaurantPagedAdapter
import som.sps.zmoto.databinding.FragmentMainBinding
import som.sps.zmoto.listeners.OnRestaurantSelection
import som.sps.zmoto.model.Restaurants
import som.sps.zmoto.network.Constants
import som.sps.zmoto.viewmodel.PageViewModel
import som.sps.zmoto.viewmodel.PageViewModelFactory
import timber.log.Timber

class PlaceholderFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener,OnRestaurantSelection {

    private lateinit var pageViewModel: PageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val id = preferences.getInt(Constants.KEY_ENTITY_ID, -1);
        val entityType = preferences.getString(Constants.KEY_ENTITY_TYPE, "city");
        val categoryId = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        val pageViewModelFactory = PageViewModelFactory(id, entityType, categoryId)
        pageViewModel =
            ViewModelProviders.of(this, pageViewModelFactory).get(PageViewModel::class.java)
        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(this)
    }

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        binding = DataBindingUtil.bind<FragmentMainBinding>(root)!!
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
    }

    private fun addObservers() {
        binding.progressBar.visibility=View.VISIBLE
        val adapter = RestaurantPagedAdapter(this)
        binding.recyclerView.adapter = adapter
        pageViewModel.itemPagedList.observe(this, Observer {
            adapter.submitList(it)
            binding.progressBar.visibility=View.GONE
            Timber.i("$it.lastKey =>${it.dataSource.isInvalid}, ${it}")
        })
    }

    override fun onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(context)
            .unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == Constants.KEY_ENTITY_TYPE) {
            val id = sharedPreferences?.getInt(Constants.KEY_ENTITY_ID, -1) ?: -1;
            val entityType =
                sharedPreferences?.getString(Constants.KEY_ENTITY_TYPE, "city") ?: "city";

            if (id != -1) {
                pageViewModel.reloadData(id, entityType)
                addObservers()
            }

        }
    }

    override fun onClick(restaurant: Restaurants) {

    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}