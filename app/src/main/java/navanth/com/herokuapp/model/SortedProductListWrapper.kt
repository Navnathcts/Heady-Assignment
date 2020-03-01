package navanth.com.herokuapp.model

data class SortedProductListWrapper (var title:String?="Product", var productList:MutableList<ProductsItem>? = null,var isProductListEmpty:Boolean?=false)