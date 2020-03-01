package navanth.com.herokuapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_base.view.*
import navanth.com.herokuapp.R
import navanth.com.herokuapp.model.CategoriesItem


class CategoriesAdapter(private val clickListener: CategoryItemClickListener) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    private var categoryList: MutableList<CategoriesItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_base, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        categoryList?.get(position)?.apply {
            holder.itemView.txtCategoryName.text = name
            holder.itemView.setOnClickListener { clickListener.onCategoryItemClick(this) }
        }

    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    fun updateCategoryList(categoryList: MutableList<CategoriesItem>?) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(v: View) : RecyclerView.ViewHolder(v)

    interface CategoryItemClickListener {
        fun onCategoryItemClick(categoryItem: CategoriesItem)
    }
}