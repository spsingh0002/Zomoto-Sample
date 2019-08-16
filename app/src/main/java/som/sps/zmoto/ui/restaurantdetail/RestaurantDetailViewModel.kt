package som.sps.zmoto.ui.restaurantdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import som.sps.zmoto.model.Restaurant
import som.sps.zmoto.network.RetrofitClient
import timber.log.Timber

class RestaurantDetailViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _categories = MutableLiveData<Restaurant>()

    val categories: LiveData<Restaurant>
        get() = _categories
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchRestaurantDetail(resId: Int) {
        _isLoading.postValue(true)
        RetrofitClient.getZomotoService().getRestaurantDetail(resId)
            .enqueue(object : Callback<Restaurant> {
                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    Timber.e(t)
                    _isLoading.postValue(false)
                }

                override fun onResponse(
                    call: Call<Restaurant>,
                    response: Response<Restaurant>
                ) {
                    if (response.isSuccessful) {
                        _categories.postValue(response.body())
                    }
                    _isLoading.postValue(false)
                }

            })
    }

}

