package navanth.com.herokuapp.respository

import navanth.com.herokuapp.model.ProductWrapperModel
import navanth.com.herokuapp.network.IProductsListService

class ProductListRepository(private val productListService: IProductsListService?):BaseRepository() {

    suspend fun getProductList():ProductWrapperModel? {
        return safeApiCall(
            call={productListService?.fetchProductsList()?.await()},
            error ="Error fetching products list"
        )
    }
}