package som.sps.zmoto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import som.sps.zmoto.model.CategoriesResponse
import som.sps.zmoto.network.RetrofitClient
import timber.log.Timber

class CategoryViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _categories = MutableLiveData<CategoriesResponse>()

    val categories: LiveData<CategoriesResponse>
        get() = _categories
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun fetchCategories() {
        _isLoading.postValue(true)
        RetrofitClient.getZomotoService().getCategories().enqueue(object :
            Callback<CategoriesResponse> {
            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                Timber.e(t)
                _isLoading.postValue(false)
            }

            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if(response.isSuccessful) {
                    _categories.postValue(response.body())
                }
                _isLoading.postValue(false)
            }

        })
    }
}