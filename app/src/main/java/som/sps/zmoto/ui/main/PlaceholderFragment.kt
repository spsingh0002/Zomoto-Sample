package som.sps.zmoto.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.R
import som.sps.zmoto.adapters.RestaurantPagedAdapter
import som.sps.zmoto.network.Constants
import som.sps.zmoto.viewmodel.PageViewModel
import som.sps.zmoto.viewmodel.PageViewModelFactory
import timber.log.Timber

class PlaceholderFragment : Fragment(),SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if(key==Constants.KEY_ENTITY_TYPE){
            val id = sharedPreferences?.getInt(Constants.KEY_ENTITY_ID, -1)?:-1;
            val entityType = sharedPreferences?.getString(Constants.KEY_ENTITY_TYPE, "city")?:"city";

            if(id!=-1){
                pageViewModel.reloadData(id, entityType)
            }

        }
    }

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val id = preferences.getInt(Constants.KEY_ENTITY_ID, -1);
        val entityType = preferences.getString(Constants.KEY_ENTITY_TYPE, "city");
        val categoryId = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        val pageViewModelFactory = PageViewModelFactory(id, entityType, categoryId)
        pageViewModel = ViewModelProviders.of(this, pageViewModelFactory).get(PageViewModel::class.java)
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter= RestaurantPagedAdapter()
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        pageViewModel.itemPagedList.observe(this, Observer {
            adapter.submitList(it)
            Timber.i("$it.lastKey =>${it.dataSource.isInvalid}, ${it}")
        })
        return root
    }


    override fun onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(context).unregisterOnSharedPreferenceChangeListener(this)
        super.onDestroy()
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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