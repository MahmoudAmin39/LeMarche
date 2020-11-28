package com.robusta.lemarche.ui.products

import android.os.Looper
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robusta.lemarche.data.Product
import com.robusta.lemarche.data.ProductsRepository

class ProductsViewModel : ViewModel() {

    private var pageNo = 1
    private var latestSearchQuery = ""

    // Data Repository
    private val productsRepository = ProductsRepository()

    // LiveData
    private val progressVisibilityData: MutableLiveData<Int> = MutableLiveData()
    private val errorVisibilityData: MutableLiveData<Int> = MutableLiveData()
    private val listViewVisibilityData: MutableLiveData<Int> = MutableLiveData()
    private val productsData: MutableLiveData<List<Product>> = MutableLiveData()

    fun getProgressVisibilityData(): LiveData<Int> = progressVisibilityData
    fun getErrorVisibilityData(): LiveData<Int> = errorVisibilityData
    fun getListViewVisibilityData(): LiveData<Int> = listViewVisibilityData
    fun getProductsData(): LiveData<List<Product>> = productsData

    fun onSearchQuerySubmit() {
        onSearchQuerySubmit(latestSearchQuery)
    }

    fun onSearchQuerySubmit(queryTerm: String) {
        if (isValidTerm(queryTerm)) {
            if (queryTerm == latestSearchQuery && pageNo == 1) {
                pageNo = 2
            } else if (latestSearchQuery != queryTerm && pageNo == 2) {
                pageNo = 1
                showProgress()
            }
            val products = productsRepository.queryProducts(queryTerm, pageNo)
            android.os.Handler(Looper.getMainLooper()).postDelayed({ showData(products) }, 300)
            latestSearchQuery = queryTerm
        } else {
            android.os.Handler(Looper.getMainLooper()).postDelayed({ showError() }, 300)
        }
    }

    private fun isValidTerm(queryTerm: String): Boolean {
        return queryTerm == "che" || queryTerm == "chee" || queryTerm == "chees" || queryTerm == "cheese" ||
                queryTerm == "mil" || queryTerm == "milk"
    }

    private fun showProgress() {
        progressVisibilityData.value = VISIBLE
        errorVisibilityData.value = GONE
        listViewVisibilityData.value = GONE
    }

    private fun showError() {
        progressVisibilityData.value = GONE
        errorVisibilityData.value = VISIBLE
        listViewVisibilityData.value = GONE
    }

    private fun showData(data: List<Product>) {
        progressVisibilityData.value = GONE
        errorVisibilityData.value = GONE
        listViewVisibilityData.value = VISIBLE
        productsData.value = data
    }
}