package navanth.com.herokuapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_product.view.*
import navanth.com.herokuapp.R
import navanth.com.herokuapp.view.adapter.ProductsAdapter.ProductViewHolder
import navanth.com.herokuapp.model.ProductsItem

class ProductsAdapter(private val products: MutableList<ProductsItem>?) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        products?.get(position)?.apply {
            holder.itemView.apply {
                txtName.text = name
                holder.itemView.context?.apply {
                    txtViewCount.text = getString(R.string.views, viewCount)
                    txtOrderCount.text = getString(R.string.orders, orderCount)
                    txtShares.text = getString(R.string.shares, shares)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    /**
     * This class will be used as a view holder representing single view for the adapter
     */
    inner class ProductViewHolder(v: View) : RecyclerView.ViewHolder(v)
}