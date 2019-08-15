package som.sps.zmoto.model

import com.google.gson.annotations.SerializedName

data class LocationSuggestionResponse(
    @SerializedName("location_suggestions")val locationSuggestionList: List<LocationSuggestion>,
    @SerializedName("status") val status:String,
    @SerializedName("has_more") val hasMore:Int,
    @SerializedName("has_total")val hasTotal:Int
    )

data class LocationSuggestion(@SerializedName("entity_type") val entityType:String,
                              @SerializedName("entity_id") val entityId:Int,
                              @SerializedName("title") val title:String,
                              @SerializedName("latitude") val latitude:Double,
                              @SerializedName("longitude")  val longitude:Double,
                              @SerializedName("city_id")  val cityId:Int,
                              @SerializedName("city_name") val cityName:String,
                              @SerializedName("country_id") val countryId:Int,
                              @SerializedName("country_name") val countryName:String
                              )
