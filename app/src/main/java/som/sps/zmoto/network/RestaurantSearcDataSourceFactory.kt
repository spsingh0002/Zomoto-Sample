package som.sps.zmoto.network

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import som.sps.zmoto.model.Restaurants

class RestaurantSearcDataSourceFactory(private  val entityId :Int, private val entityType:String, private val categoryId:Int) : DataSource.Factory<Int,Restaurants>() {

    val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, Restaurants>> =
        MutableLiveData()


     override fun create(): DataSource<Int, Restaurants> {
        val itemDataSource =
            RestaurantSearchDataSource(entityId, entityType, categoryId)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }
}