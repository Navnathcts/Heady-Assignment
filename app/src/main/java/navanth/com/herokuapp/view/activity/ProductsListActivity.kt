package navanth.com.herokuapp.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_products.*
import navanth.com.herokuapp.Utils.Constants
import navanth.com.herokuapp.R
import navanth.com.herokuapp.view.adapter.ProductsAdapter
import navanth.com.herokuapp.model.ProductsItem
import navanth.com.herokuapp.viewmodel.SortedProductsListViewModel
import java.util.*

class ProductsListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)

        ViewModelProvider(this).get(SortedProductsListViewModel::class.java).let {
            it.getSortedProductList(intent.getSerializableExtra(Constants.PRODUCT_LIST))
            it.sortedProductList.observe(this@ProductsListActivity, androidx.lifecycle.Observer {
                Objects.requireNonNull(supportActionBar)?.setTitle(it.title)
                if (it.isProductListEmpty == true) showNoProductAvailableMessage() else showProductList(it.productList)
            })
        }
    }

    /**
     * This will show sorted product list.
     */
    private fun showProductList(productList: MutableList<ProductsItem>?) {
        rvSortedProductList.visibility = View.VISIBLE
        txtNoProductMessage.visibility = View.GONE
        rvSortedProductList?.apply {
            this.layoutManager = LinearLayoutManager(this@ProductsListActivity)
            this.adapter = ProductsAdapter(productList)
        }
    }

    /**
     * This will show no data available message.
     */
    private fun showNoProductAvailableMessage() {
        rvSortedProductList.visibility = View.GONE
        txtNoProductMessage.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}