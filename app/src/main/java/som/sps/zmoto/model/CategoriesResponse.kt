package som.sps.zmoto.model

import com.google.gson.annotations.SerializedName

data class Category(@SerializedName("id") var id:Int, @SerializedName("nmae")var name:String)

data class CategoriesResponse(@SerializedName("categories") var categories:List<Categories>)

data class Categories(@SerializedName("categories")var category: Category)