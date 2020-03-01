package navanth.com.herokuapp.network

object ProductListService {
    fun productListServiceProvider() = RetrofitProvider.getRetrofit().create(IProductsListService::class.java)
}