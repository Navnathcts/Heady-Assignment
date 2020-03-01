package navanth.com.herokuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import navanth.com.herokuapp.model.ProductWrapperModel
import navanth.com.herokuapp.model.ProductsItem
import navanth.com.herokuapp.model.StatusInfo
import navanth.com.herokuapp.network.ProductListService
import navanth.com.herokuapp.respository.ProductListRepository

class ProductListViewModel : BaseViewModel() {
    //initialize ProductListRepo repo
    private val productListRepository: ProductListRepository =
        ProductListRepository(ProductListService.productListServiceProvider())
    //live data that will be populated as product list updates
    val productList = MutableLiveData<ProductWrapperModel?>()

    fun getProductList() {
        ///launch the coroutine scope
        coroutineScope.launch {
            productList.postValue(ProductWrapperModel(statusInfo = StatusInfo(showLoader = true)))
            //post the value inside live data
            val productWrapperModel = productListRepository.getProductList()
            computeFinalProductList(productWrapperModel)
            if (productWrapperModel == null) {
                productList.postValue(
                    ProductWrapperModel(
                        statusInfo = StatusInfo(
                            showLoader = false,
                            isNoProduct = true
                        )
                    )
                )
            } else {
                productWrapperModel.statusInfo = StatusInfo(showLoader = false,isNoProduct = false)
                productList.postValue(productWrapperModel)
            }


        }
    }

    private fun computeFinalProductList(productWrapper: ProductWrapperModel?) {
        val productDataMap: MutableMap<Int, ProductsItem>? = mutableMapOf()
        productWrapper?.apply {
            //for products sorted with category.
            categories?.forEach { category ->
                category.products?.let { productItemsList ->
                    productItemsList.forEach { productWithCategory ->
                        productDataMap?.put(productWithCategory.id, productWithCategory)
                    }
                }
                //product sorted with ranking.
                rankings?.forEach { ranking ->
                    ranking.products?.forEach { productWithRank ->
                        //if product exist in ranking list, compute product details.
                        if (productDataMap?.containsKey(productWithRank.id) == true) {
                            productDataMap[productWithRank.id]?.apply {
                                orderCount = productWithRank.orderCount
                                viewCount = productWithRank.viewCount
                                shares = productWithRank.shares
                            }
                        }
                    }

                }
            }
            this.productDataMap = productDataMap
        }
    }

    fun cancelRequests() = coroutineContext.cancel()
}