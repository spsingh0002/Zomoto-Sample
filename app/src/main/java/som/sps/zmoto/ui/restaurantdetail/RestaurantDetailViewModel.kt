package som.sps.zmoto.ui.restaurantdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import som.sps.zmoto.RestaurantDetail
import som.sps.zmoto.model.RestaurantDetailResponse
import som.sps.zmoto.network.RetrofitClient
import timber.log.Timber

class RestaurantDetailViewModel : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    private val _categories = MutableLiveData<RestaurantDetailResponse>()

    val categories: LiveData<RestaurantDetailResponse>
        get() = _categories
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchRestaurantDetail(resId: Int) {
        _isLoading.postValue(true)
        RetrofitClient.getZomotoService().getRestaurantDetail(resId)
            .enqueue(object : Callback<RestaurantDetailResponse> {
                override fun onFailure(call: Call<RestaurantDetailResponse>, t: Throwable) {
                    Timber.e(t)
                    _isLoading.postValue(false)
                }

                override fun onResponse(
                    call: Call<RestaurantDetailResponse>,
                    response: Response<RestaurantDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        _categories.postValue(response.body())
                    }
                    _isLoading.postValue(false)
                }

            })
    }

}

