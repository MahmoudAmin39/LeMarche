package com.robusta.lemarche.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.robusta.lemarche.R
import com.robusta.lemarche.ui.product_search.ProductSearchViewModel

class ProductsFragment : Fragment() {

    private val activityViewModel: ProductSearchViewModel by activityViewModels()

    private val fragmentViewModel: ProductsViewModel by lazy {
        ViewModelProvider(this).get(ProductsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get the search term
        activityViewModel.getSearchTermLiveData()
            .observe(viewLifecycleOwner, { fragmentViewModel.onSearchQuerySubmit(it) })

        // Observe for views changes
        fragmentViewModel.getProgressVisibilityData().observe(viewLifecycleOwner, {})
        fragmentViewModel.getListViewVisibilityData().observe(viewLifecycleOwner, {})
        fragmentViewModel.getErrorVisibilityData().observe(viewLifecycleOwner, {})

        // Observe for data changes
        fragmentViewModel.getProductsData().observe(viewLifecycleOwner, {
            // TODO: 11/28/20 Show the data through the adapter
        })
    }
}
