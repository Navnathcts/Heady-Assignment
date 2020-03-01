package navanth.com.herokuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import navanth.com.herokuapp.Utils.Constants
import navanth.com.herokuapp.model.*
import java.io.Serializable

class SortedProductsListViewModel : BaseViewModel() {

    //live data that will be populated as product list updates
    val sortedProductList = MutableLiveData<SortedProductListWrapper>()
    //default sorting will be based on id
    private var mSortBy: String = Constants.ID
    private var mTitle: String? = null
    fun getSortedProductList(productWrapper: Serializable?) {
        ///launch the coroutine scope
        coroutineScope.launch {
            var products: MutableList<ProductsItem>? = null
            if (productWrapper is CategoriesItem) {
                mTitle = productWrapper.name
                products = productWrapper.products
            } else if (productWrapper is RankingsItem) {
                mTitle = productWrapper.ranking
                products = productWrapper.products
                mSortBy = productWrapper.ranking
            }
            sortProductList(products)
            sortedProductList.postValue(
                SortedProductListWrapper(
                    mTitle,
                    products,
                    products?.isEmpty()
                )
            )
        }
    }

    /**
     * This will sort product list based on either most views, most orders, most shares
     * default sorting will be based on id.
     *
     */
    private fun sortProductList(products: MutableList<ProductsItem>?) {
        products?.forEach { productItem ->
            ProductDataHolder.productDataMap?.apply {
                if (containsKey(productItem.id)) {
                    get(productItem.id)?.let {
                        products[products.indexOf(productItem)] = it
                    }
                }
            }
        }
        products?.sortWith(Comparator sort@{ o1: ProductsItem, o2: ProductsItem ->
            val val1: Int
            val val2: Int
            when (mSortBy) {
                Constants.VIEWS -> {
                    val1 = o1.viewCount
                    val2 = o2.viewCount
                }
                Constants.ORDERS -> {
                    val1 = o1.orderCount
                    val2 = o2.orderCount
                }
                Constants.SHARES -> {
                    val1 = o1.shares
                    val2 = o2.shares
                }
                else -> {
                    val1 = o1.id
                    val2 = o2.id
                }
            }
            when {
                val1 < val2 -> {
                    return@sort 1
                }
                val1 > val2 -> {
                    return@sort -1
                }
                else -> {
                    return@sort 0
                }
            }
        })
    }
}