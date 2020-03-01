package navanth.com.herokuapp.network

import kotlinx.coroutines.Deferred
import navanth.com.herokuapp.Utils.Constants
import navanth.com.herokuapp.model.ProductWrapperModel
import retrofit2.Response
import retrofit2.http.GET

interface IProductsListService {
    @GET(value = Constants.JSON)
    fun fetchProductsList(): Deferred<Response<ProductWrapperModel?>>
}