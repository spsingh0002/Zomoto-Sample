package som.sps.zmoto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import som.sps.zmoto.model.Restaurants
import som.sps.zmoto.network.Constants
import som.sps.zmoto.network.RestaurantSearcDataSourceFactory

class PageViewModel(  entityId:Int, entityType:String,private val  categoryId:Int) : ViewModel() {


   lateinit var itemPagedList: LiveData<PagedList<Restaurants>>
    lateinit  var liveDataSource: LiveData<PageKeyedDataSource<Int, Restaurants>>

    init {
        loadData(entityId,entityType)
    }

    private fun loadData(entityId: Int, entityType: String) {
        val itemDataSourceFactory =
            RestaurantSearcDataSourceFactory(entityId, entityType, categoryId)

        //getting the live data source from data source factory
        liveDataSource =itemDataSourceFactory.itemLiveDataSource

        //Getting PagedList config
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(2)
            .setInitialLoadSizeHint(Constants.PAGE_SIZE)
            .setPageSize(Constants.PAGE_SIZE).build()

        //Building the paged list
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
            .build()
    }

    fun reloadData(entityId: Int, entityType: String) {
        loadData(entityId, entityType)
    }
}