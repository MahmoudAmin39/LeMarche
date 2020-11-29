package com.robusta.lemarche.data

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_name") val name: String,
    @SerializedName("product_image_url") val imageUrl: String?,
    @SerializedName("product_price") val price: String,
    @SerializedName("product_rating") val rating: Float?,
    @SerializedName("product_no_of_raters") val noOfReviews: Int?,
    @SerializedName("product_merchant") val merchantName: String
)