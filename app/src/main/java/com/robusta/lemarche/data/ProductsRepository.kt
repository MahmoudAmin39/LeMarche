package com.robusta.lemarche.data

import org.json.JSONArray
import org.json.JSONObject

class ProductsRepository {

    private val allProducts: MutableList<Product> = mutableListOf()

    init {
        val jsonArrayString = ProductsJson.ALL_PRODUCTS
        val productsJson = JSONArray(jsonArrayString)
        for (index in 0..productsJson.length()) {
            val productJson = productsJson[index] as JSONObject
            val product = Product.fromJson(productJson)
            allProducts.add(product)
        }
    }

    fun queryProducts(queryTerm: String, pageNo: Int): List<Product> {
        /*if (pageNo == 1) {
            return allProducts.filter { it.name.contains(queryTerm) }.take(10)
        } else if (pageNo == 2) {
            return allProducts.filter { it.name.contains(queryTerm) }.take(20).takeLast(10)
        }
        return emptyList()*/
        return allProducts
    }
}