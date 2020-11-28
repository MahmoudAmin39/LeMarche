package com.robusta.lemarche.data

import org.json.JSONObject

data class Product(
    val name: String,
    val imageUrl: String?,
    val price: String,
    val rating: Float?,
    val noOfReviews: Int?,
    val merchantName: String
) {
    companion object {

        fun fromJson(productJson: JSONObject): Product {
            val name = productJson.optString("product_name")
            val imageUrl = productJson.optString("product_image_url")
            val price = productJson.optString("product_price")
            val rating = productJson.optDouble("product_rating")
            val noOfReviews = productJson.optInt("product_no_of_raters")
            val merchantName = productJson.optString("product_merchant")
            return Product(name, imageUrl, price, rating.toFloat(), noOfReviews, merchantName)
        }
    }
}