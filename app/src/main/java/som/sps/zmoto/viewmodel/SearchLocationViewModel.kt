package som.sps.zmoto.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import som.sps.zmoto.model.CategoriesResponse
import som.sps.zmoto.model.LocationSuggestionResponse
import som.sps.zmoto.network.RetrofitClient
import timber.log.Timber

class SearchLocationViewModel : ViewModel() {

    private val _locationsMutableLiveData = MutableLiveData<LocationSuggestionResponse>()
    private val _isLoading = MutableLiveData<Boolean>()

    val locationMutableLiveData: LiveData<LocationSuggestionResponse>
        get() = _locationsMutableLiveData

    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun searchLocation(query: String) {
        _isLoading.postValue(true)
        RetrofitClient.getZomotoService().getLocationSuggestions(query = query).enqueue(object :
            Callback<LocationSuggestionResponse> {
            override fun onFailure(call: Call<LocationSuggestionResponse>, t: Throwable) {
                Timber.e(t)
                _isLoading.postValue(false)
            }

            override fun onResponse(
                call: Call<LocationSuggestionResponse>,
                response: Response<LocationSuggestionResponse>
            ) {
                if (response.isSuccessful) {
                    _locationsMutableLiveData.postValue(response.body())
                }
                _isLoading.postValue(false)
            }

        })
    }

}
