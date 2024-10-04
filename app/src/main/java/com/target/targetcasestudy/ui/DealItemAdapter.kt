package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.domain.model.DealProductItemModel
import com.target.targetcasestudy.ui.state.OnDealClickListener

/*


class DealItemAdapter() : RecyclerView.Adapter<DealItemViewHolder>() {
    private var deals: List<DealProductItemModel> = listOf()

    fun setDeals(deals: List<DealProductItemModel>) {
        this.deals = deals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = deals[position]
        viewHolder.bind(item)
    }
}


class DealItemViewHolder(private val binding: DealListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val green =
        ContextCompat.getColor(binding.dealListItemImageView.context, R.color.colorAccent)
    private val gray =
        ContextCompat.getColor(binding.dealListItemImageView.context, R.color.gray_color)

    fun bind(deal: DealProductItemModel) {
        deal.run {
            binding.dealListItemTitle.text = title
            if (salePrice != null) {
                binding.dealListItemSalePrice.text =
                    salePrice.displayString
                "reg ${regularPrice.displayString}".also { binding.dealListItemRegPrice.text = it }
            } else {
                binding.dealListItemSalePrice.text =
                    regularPrice.displayString
            }

            binding.dealListItemSellMode.text = fulfillment

            binding.dealListItemAvailability.text = buildItemAvailabiltyString()

            Glide.with(binding.dealListItemImageView.context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                .into(binding.dealListItemImageView)
        }

    }

    private fun DealProductItemModel.buildItemAvailabiltyString() =
        buildSpannedString {
            color(green) {
                append(availability)
            }
            color(gray) {
                append(" in aisle ${aisle.uppercase()}")
            }
        }
}
*/




class DealItemAdapter(private val listener: OnDealClickListener) :
    RecyclerView.Adapter<DealItemViewHolder>() {
    private var deals: List<DealProductItemModel> = listOf()

    fun setDeals(deals: List<DealProductItemModel>) {
        this.deals = deals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(binding, listener) // Pass the listener
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = deals[position]
        viewHolder.bind(item)
    }
}


class DealItemViewHolder(
    private val binding: DealListItemBinding,
    private val listener: OnDealClickListener
) :
    RecyclerView.ViewHolder(binding.root) {


    private val green =
        ContextCompat.getColor(binding.dealListItemImageView.context, R.color.colorAccent)
    private val gray =
        ContextCompat.getColor(binding.dealListItemImageView.context, R.color.gray_color)

    fun bind(deal: DealProductItemModel) {
        deal.run {
            binding.dealListItemTitle.text = title
            if (salePrice != null) {
                binding.dealListItemSalePrice.text =
                    salePrice.displayString
                "reg ${regularPrice.displayString}".also { binding.dealListItemRegPrice.text = it }
            } else {
                binding.dealListItemSalePrice.text =
                    regularPrice.displayString
            }

            binding.dealListItemSellMode.text = fulfillment

            binding.dealListItemAvailability.text = buildItemAvailabiltyString()

            Glide.with(binding.dealListItemImageView.context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                .into(binding.dealListItemImageView)

            binding.root.setOnClickListener {
                listener.onDealClick(deal)

            }
        }

    }

    private fun DealProductItemModel.buildItemAvailabiltyString() =
        buildSpannedString {
            color(green) {
                append(availability)
            }
            color(gray) {
                append(" in aisle ${aisle.uppercase()}")
            }
        }
}
