package som.sps.zmoto

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import som.sps.zmoto.databinding.SearchLocationFragmentBinding
import som.sps.zmoto.listeners.SearchSelectionListener
import som.sps.zmoto.viewmodel.SearchLocationViewModel


class SearchLocationFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(query: String): SearchLocationFragment {
            val searchLocationFragment = SearchLocationFragment()
            val bundle = Bundle()
            bundle.putString("query", query)
            searchLocationFragment.arguments = bundle
            return searchLocationFragment
        }
    }

    private lateinit var searchSelectionListener: SearchSelectionListener
    private lateinit var searchLocationFragmentBinding: SearchLocationFragmentBinding
    private lateinit var viewModel: SearchLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchLocationFragmentBinding = DataBindingUtil.inflate<SearchLocationFragmentBinding>(
            inflater,
            R.layout.search_location_fragment,
            container,
            false
        )
        searchLocationFragmentBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        return searchLocationFragmentBinding.root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SearchSelectionListener) {
            searchSelectionListener = context
        } else {
            throw IllegalAccessException("attached activity should implement SearchSelectionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchLocationViewModel::class.java)
        searchLocationFragmentBinding.listener = searchSelectionListener
        searchLocationFragmentBinding.model = viewModel
        searchLocationFragmentBinding.lifecycleOwner = this

        viewModel.searchLocation(arguments!!.getString("query"))
    }

}
