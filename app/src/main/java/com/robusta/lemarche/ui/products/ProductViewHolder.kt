package com.robusta.lemarche.ui.products

import android.graphics.Color
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.robusta.lemarche.R
import com.robusta.lemarche.data.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindProductToViews(product: Product, query: String) {
        val startIndex = product.name.indexOf(query, 0, true)
        val spannableProductName = SpannableString(product.name)
        spannableProductName.setSpan(BackgroundColorSpan(Color.YELLOW), startIndex, (startIndex + query.length), 0)
        itemView.textView_product_name.text = spannableProductName
        itemView.textView_product_price.text = product.price
        itemView.textView_product_no_of_reviewers.text = String.format("(%d)", product.noOfReviews)
        if (product.rating != null) {
            itemView.ratingBar_product.rating = product.rating
        }

        if(product.imageUrl != null) {
            Glide.with(itemView).load(product.imageUrl).placeholder(R.drawable.ic_cheese).into(itemView.imageView_product_image)
        }
    }
}