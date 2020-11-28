package com.robusta.lemarche.data

data class Product(
    val name: String,
    val imageUrl: String?,
    val price: String,
    val rating: Float?,
    val noOfReviews: Int?,
    val merchantName: String
)