package navanth.com.herokuapp.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import navanth.com.herokuapp.R
import navanth.com.herokuapp.Utils.Constants.PRODUCT_LIST
import navanth.com.herokuapp.Utils.checkInternetConnection
import navanth.com.herokuapp.Utils.openActivity
import navanth.com.herokuapp.Utils.showSnackBar
import navanth.com.herokuapp.model.CategoriesItem
import navanth.com.herokuapp.model.ProductDataHolder
import navanth.com.herokuapp.model.RankingsItem
import navanth.com.herokuapp.model.StatusInfo
import navanth.com.herokuapp.view.adapter.CategoriesAdapter
import navanth.com.herokuapp.view.adapter.RankingAdapter
import navanth.com.herokuapp.viewmodel.ProductListViewModel


class MainActivity : AppCompatActivity(), CategoriesAdapter.CategoryItemClickListener,
    RankingAdapter.RankingItemClickListener {
    private var categoryAdapter: CategoriesAdapter? = null
    private var rankingAdapter: RankingAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvCategoryList?.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        ViewModelProvider(this).get(ProductListViewModel::class.java).let { viewModel ->
            if (checkInternetConnection()) {
                //fetch product list
                viewModel.getProductList()
                //receive product list and set on UI.
                viewModel.productList.observe(this@MainActivity, Observer {
                    checkIsProductsAvailable(it?.statusInfo)
                    it?.let {
                        //saving complete list in data holder
                        ProductDataHolder.productDataMap = it.productDataMap
                        //setting default adapter as category adapter.
                        setCategoryAdapter(it.categories)
                        //event click listener for swProdListWithRank Switch.
                        swProdListWithRank?.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
                            when (isChecked) {
                                true -> {
                                    setRankingAdapter(it.rankings)
                                }
                                false -> {
                                    setCategoryAdapter(it.categories)
                                }
                            }
                        }
                    }
                })
                //click listener for btnRefreshData Button
                btnRefreshData?.setOnClickListener { viewModel.getProductList() }
            } else {
                showSnackBar(
                    view = rlContainer,
                    message = getString(R.string.no_connection_message),
                    messageColor = Color.YELLOW
                )
            }

        }
    }

    /**
     * This will handle UI view visibilities based on product list.
     */
    private fun checkIsProductsAvailable(statusInfo: StatusInfo?) {
        statusInfo?.let {
            if (it.showLoader == true) {
                progressBar?.visibility = View.VISIBLE
                txtLoaderMessage?.visibility = View.VISIBLE
                rvCategoryList?.visibility = View.GONE
                txtNoDataMessage?.visibility = View.GONE
                btnRefreshData?.visibility = View.GONE
            } else {
                progressBar?.visibility = View.GONE
                txtLoaderMessage?.visibility = View.GONE
                it.isNoProduct?.let {
                    if (it) {
                        rvCategoryList?.visibility = View.GONE
                        swProdListWithRank?.visibility = View.GONE
                        txtNoDataMessage?.visibility = View.VISIBLE
                        btnRefreshData?.visibility = View.VISIBLE
                    } else {
                        rvCategoryList?.visibility = View.VISIBLE
                        swProdListWithRank?.visibility = View.VISIBLE
                        txtNoDataMessage?.visibility = View.GONE
                        btnRefreshData?.visibility = View.GONE
                    }
                }
            }

        }

    }

    /**
     * This will set category items.
     */
    private fun setCategoryAdapter(categories: MutableList<CategoriesItem>?) {
        if (categoryAdapter == null) {
            categoryAdapter = CategoriesAdapter(this)
        }
        categoryAdapter?.apply {
            rvCategoryList.adapter = this
            updateCategoryList(categories)
        }
    }

    /**
     * This will set product list based on rank.
     */
    private fun setRankingAdapter(rankings: MutableList<RankingsItem>?) {
        if (rankingAdapter == null) {
            rankingAdapter = RankingAdapter(this)
        }
        rankingAdapter?.apply {
            rvCategoryList.adapter = this
            updateRankingList(rankings)
        }
    }

    override fun onCategoryItemClick(categoryItem: CategoriesItem) {
        openActivity(ProductsListActivity::class.java) {
            putSerializable(PRODUCT_LIST, categoryItem)
        }
    }

    override fun onRankingItemClick(rankingsItem: RankingsItem) {
        openActivity(ProductsListActivity::class.java) {
            putSerializable(PRODUCT_LIST, rankingsItem)
        }
    }


}
