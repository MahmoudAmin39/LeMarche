package com.robusta.lemarche.ui.splash

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robusta.lemarche.R
import com.robusta.lemarche.data.Product
import com.robusta.lemarche.data.ProductsRepository
import com.robusta.lemarche.data.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashViewModel : ViewModel() {

    private val progressVisibilityLiveData: MutableLiveData<Int> = MutableLiveData()
    private val textViewVisibilityLiveData: MutableLiveData<Int> = MutableLiveData()

    private val textViewTextData: MutableLiveData<Int> = MutableLiveData()

    fun getProgressBarVisibility() : LiveData<Int> = progressVisibilityLiveData
    fun getTextViewVisibility() : LiveData<Int> = textViewVisibilityLiveData
    fun getTextViewTextData() : LiveData<Int> = textViewTextData

    fun fetchProducts() {
        progressVisibilityLiveData.value = VISIBLE
        textViewVisibilityLiveData.value = GONE
        RetrofitClient.getProductsService().listProducts()
            ?.enqueue(object : Callback<List<Product>> {
                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    if (response.code() == 200 && response.body() != null) {
                        progressVisibilityLiveData.value = GONE
                        textViewVisibilityLiveData.value = VISIBLE

                        textViewTextData.value = R.string.msg_search

                        ProductsRepository.get().allProducts = response.body()!!
                    } else {
                        showError()
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    showError()
                }
            })
    }

    private fun showError() {
        progressVisibilityLiveData.value = GONE
        textViewVisibilityLiveData.value = VISIBLE

        textViewTextData.value = R.string.msg_error
    }
}