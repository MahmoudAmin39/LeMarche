package com.robusta.lemarche.ui.products

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.robusta.lemarche.data.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindProductToViews(product: Product) {
        itemView.textView_product_name.text = product.name
        itemView.textView_product_price.text = product.price
        itemView.textView_product_no_of_reviewers.text = String.format("(%d)", product.noOfReviews)
        if (product.rating != null) {
            itemView.ratingBar_product.rating = product.rating
        }
    }
}