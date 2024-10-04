package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.data.local.DealItem
import com.target.targetcasestudy.data.local.StaticData
import com.target.targetcasestudy.databinding.DealListItemBinding

/*
class DealItemAdapter : RecyclerView.Adapter<DealItemViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.deal_list_item, parent, false)
    return DealItemViewHolder(view)
  }

  override fun getItemCount(): Int {
    return StaticData.deals.size
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = StaticData.deals[position]
    viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
    viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_price).text = item.price
  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}*/


class DealItemAdapter : RecyclerView.Adapter<DealItemViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = DealListItemBinding.inflate(inflater, parent, false)
    return DealItemViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return StaticData.deals.size
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = StaticData.deals[position]
    viewHolder.bind(item)
  }
}

class DealItemViewHolder(private val binding: DealListItemBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(deal: DealItem) {
    binding.dealListItemTitle.text = deal.title
    binding.dealListItemSalePrice.text = deal.price
    Glide.with(binding.dealListItemImageView.context)
      .load(deal.imageUrl)
      .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
      .into(binding.dealListItemImageView)
  }
}
