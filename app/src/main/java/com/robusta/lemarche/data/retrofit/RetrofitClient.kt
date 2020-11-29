package com.robusta.lemarche.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private var retrofit: Retrofit? = null
        private var productsService: ProductsService? = null

        private fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://5fc2413f921006001686958f.mockapi.io/app/lemarche/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getProductsService(): ProductsService {
            if (productsService == null) {
                productsService = getRetrofit().create(ProductsService::class.java)
            }
            return productsService!!
        }
    }
}