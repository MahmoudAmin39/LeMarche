package com.robusta.lemarche.data

class ProductsRepository private constructor() {

    var allProducts: List<Product> = mutableListOf()

    companion object {

        private var instance: ProductsRepository? = null

        fun get(): ProductsRepository {
            if (instance == null) {
                instance = ProductsRepository()
            }
            return instance!!
        }
    }

    fun queryProducts(queryTerm: String, pageNo: Int): List<Product> {
        if (pageNo == 1) {
            return allProducts.filter { it.name.toLowerCase().contains(queryTerm.toLowerCase()) }
                .take(10)
        } else if (pageNo == 2) {
            return allProducts.filter { it.name.toLowerCase().contains(queryTerm.toLowerCase()) }
                .take(20)
        }
        return emptyList()
    }
}