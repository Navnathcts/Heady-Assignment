package navanth.com.herokuapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_base.view.*
import navanth.com.herokuapp.R
import navanth.com.herokuapp.model.RankingsItem


class RankingAdapter(private val clickListener: RankingItemClickListener) :
    RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {
    private var rankingList: MutableList<RankingsItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        return RankingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_base, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {

        rankingList?.get(position)?.apply {
            holder.itemView.txtCategoryName.text = ranking
            holder.itemView.setOnClickListener { clickListener.onRankingItemClick(this) }
        }

    }

    override fun getItemCount(): Int {
        return rankingList?.size ?: 0
    }

    fun updateRankingList(rankingList: MutableList<RankingsItem>?) {
        this.rankingList = rankingList
        notifyDataSetChanged()
    }

    inner class RankingViewHolder(v: View) : RecyclerView.ViewHolder(v)

    interface RankingItemClickListener {
        fun onRankingItemClick(rankingsItem: RankingsItem)
    }
}