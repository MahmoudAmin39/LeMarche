package com.robusta.lemarche.data.retrofit

import com.robusta.lemarche.data.Product
import retrofit2.Call
import retrofit2.http.GET


interface ProductsService {

    @GET("products")
    fun listProducts(): Call<List<Product>>?
}