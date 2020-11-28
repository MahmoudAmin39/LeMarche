package com.robusta.lemarche.ui.product_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductSearchViewModel : ViewModel() {

    private val searchTerm: MutableLiveData<String> = MutableLiveData()

    fun getSearchTermLiveData(): LiveData<String> {
        return searchTerm
    }

    fun onQueryTextSubmit(queryTerm: String) {
        searchTerm.postValue(queryTerm)
    }
}