package som.sps.zmoto.model

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("results_found") var resultsFound: Int
    , @SerializedName("results_start") var resultsStart: Int
    , @SerializedName("results_shown") var resultsShown: Int
    , @SerializedName("restaurants") var restaurants: List<Restaurants>
)

data class RestaurantLocation(
    @SerializedName("address") var address: String
    , @SerializedName("locality") var locality: String
    , @SerializedName("city") var city: String
    , @SerializedName("city_id") var city_id: Int
    , @SerializedName("latitude") var latitude: String
    , @SerializedName("longitude") var longitude: String
    , @SerializedName("zipcode") var zipCode: String
    , @SerializedName("country_id") var countryId: Int
    , @SerializedName("locality_verbose") var localityVerbose: String
)


data class UserRating(
    @SerializedName("aggregate_rating") var aggregateRating: String
    , @SerializedName("rating_text") var ratingText: String
    , @SerializedName("rating_color") var ratingColor: String
    , @SerializedName("votes") var votes: String

)


data class Photo(
    @SerializedName("id") var id: String
    , @SerializedName("url") var url: String
    , @SerializedName("thumb_url") var thumbUrl: String
    , @SerializedName("res_id") var resId: String
    , @SerializedName("caption") var caption: String
    , @SerializedName("timestamp") var timestamp: String
    , @SerializedName("friendly_time") var friendlyTime: String
    , @SerializedName("width") var width: String
    , @SerializedName("height") var height: String
    , @SerializedName("comments_count") var commentsCount: String
    , @SerializedName("likes_count") var likesCount: String
    , @SerializedName("user") var user: User
)


data class User(
    @SerializedName("name") var name: String
    , @SerializedName("zomato_handle") var zomatoHandle: String
    , @SerializedName("foodie_level") var foodieLevel: String
    , @SerializedName("foodie_level_num") var foodieLevelNum: String
    , @SerializedName("profile_url") var profileUrl: String
    , @SerializedName("profile_deeplink") var profileDeeplink: String
    , @SerializedName("profile_image") var profileImage: String

)

data class Restaurants(@SerializedName("restaurant")var restaurant: Restaurant)

data class Restaurant(
    @SerializedName("id") var id: Int
    , @SerializedName("name") var name: String
    , @SerializedName("url") var url: String
    , @SerializedName("location") var location: RestaurantLocation
    ,@SerializedName("switch_to_order_menu") var switch_to_order_menu:Int
    ,@SerializedName("timings") var timings:String
    , @SerializedName("average_cost_for_two") var averageCostForTwo: Int
    , @SerializedName("price_range") var priceRange: Int
    , @SerializedName("currency") var currency: String
    ,@SerializedName("highlights")var highlights:List<String>
    ,@SerializedName("opentable_support")var opentable_support:Int
    ,@SerializedName("is_zomato_book_res")var is_zomato_book_res:Int
    ,@SerializedName("mezzo_provider") var mezzo_provider:String
    ,@SerializedName("is_book_form_web_view")var is_book_form_web_view:Int
    ,@SerializedName("book_form_web_view_url") var book_form_web_view_url:String
    ,@SerializedName("book_again_url") var book_again_url:String
    , @SerializedName("thumb") var thumb: String
    , @SerializedName("featured_image") var featuredImage: String
    , @SerializedName("photos_url") var photosUrl: String
    , @SerializedName("menu_url") var menuUrl: String
    , @SerializedName("events_url") var eventsUrl: String
    , @SerializedName("user_rating") var userRating: UserRating
    , @SerializedName("has_online_delivery") var hasOnlineDelivery: String
    , @SerializedName("is_delivering_now") var isDeliveringNow: Int
    , @SerializedName("has_table_booking") var hasTableBooking: Int
    , @SerializedName("cuisines") var cuisines: String
    , @SerializedName("all_reviews_count") var allReviewsCount: Int
    , @SerializedName("photo_count") var photoCount: Int
    , @SerializedName("phone_numbers") var phoneNumbers: String
    , @SerializedName("photos") var photos: List<Photos>
    , @SerializedName("all_reviews") var reviews: Reviews
)

data class Reviews(
    @SerializedName("review") var review:List<Review>
)

data class Photos(@SerializedName("photo") var photo:Photo)

data class Review(
    @SerializedName("rating") var rating: String
    , @SerializedName("review_text") var reviewText: String
    , @SerializedName("id") var id: String
    , @SerializedName("rating_color") var ratingColor: String
    , @SerializedName("review_time_friendly") var reviewTimeFriendly: String
    , @SerializedName("rating_text") var ratingText: String
    , @SerializedName("timestamp") var timestamp: String
    , @SerializedName("likes") var likes: String
    , @SerializedName("user") var user: User
    , @SerializedName("comments_count") var commentsCount: String
)

