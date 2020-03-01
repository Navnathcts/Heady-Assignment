package navanth.com.herokuapp.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class VariantsItem(
    @SerializedName("color")
    val color: String = "",
    @SerializedName("size")
    val size: Int = 0,
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("id")
    val id: Int = 0
) : Serializable


data class ProductWrapperModel(
    @SerializedName("rankings")
    val rankings: MutableList<RankingsItem>?=null,
    @SerializedName("categories")
    val categories: MutableList<CategoriesItem>?=null,
    @SerializedName("productDataMap")
    var productDataMap: MutableMap<Int, ProductsItem>? = null,
    @SerializedName("errorInfo")
    var statusInfo: StatusInfo? = null
)


data class CategoriesItem(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("products")
    val products: MutableList<ProductsItem>?
) : Serializable


data class Tax(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("value")
    val value: Double = 0.0
) : Serializable


data class RankingsItem(
    @SerializedName("ranking")
    val ranking: String = "",
    @SerializedName("products")
    val products: MutableList<ProductsItem>?
) : Serializable


data class ProductsItem(
    @SerializedName("date_added")
    val dateAdded: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("tax")
    val tax: Tax,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("variants")
    val variants: MutableList<VariantsItem>?
) : Serializable {
    @SerializedName("view_count")
    @Expose
    var viewCount = 0
    @SerializedName("order_count")
    @Expose
    var orderCount = 0
    @SerializedName("shares")
    @Expose
    var shares = 0
}


