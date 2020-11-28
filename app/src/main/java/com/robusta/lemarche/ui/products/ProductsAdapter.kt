package com.robusta.lemarche.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robusta.lemarche.R
import com.robusta.lemarche.data.Product

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    lateinit var query: String
    private val products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProductToViews(products[position], query)
    }

    override fun getItemCount(): Int = products.size

    fun addProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}