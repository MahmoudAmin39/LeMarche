package com.robusta.lemarche.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.robusta.lemarche.R
import com.robusta.lemarche.ui.product_search.ProductSearchViewModel
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private val activityViewModel: ProductSearchViewModel by activityViewModels()

    private val fragmentViewModel: ProductsViewModel by lazy {
        ViewModelProvider(this).get(ProductsViewModel::class.java)
    }

    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init the adapter
        productsAdapter = ProductsAdapter()
        recyclerView_products.layoutManager = LinearLayoutManager(activity)
        recyclerView_products.adapter = productsAdapter

        // Implement the Back to top FAB logic
        recyclerView_products.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val recyclerViewLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstItemPosition = recyclerViewLayoutManager.findFirstCompletelyVisibleItemPosition()
                if (firstItemPosition != 0) {
                    fab_back_to_top.show()
                } else {
                    fab_back_to_top.hide()
                }
            }
        })
        
        // Add a click listener for the FAB
        fab_back_to_top.setOnClickListener { (recyclerView_products.layoutManager as LinearLayoutManager).scrollToPosition(0) }

        // Get the search term
        activityViewModel.getSearchTermLiveData()
            .observe(viewLifecycleOwner, { fragmentViewModel.onSearchQuerySubmit(it) })

        // Observe for views changes
        fragmentViewModel.getProgressVisibilityData()
            .observe(viewLifecycleOwner, { progressBar.visibility = it })
        fragmentViewModel.getListViewVisibilityData()
            .observe(viewLifecycleOwner, { recyclerView_products.visibility = it })
        fragmentViewModel.getErrorVisibilityData()
            .observe(viewLifecycleOwner, { layout_errorView.visibility = it })

        // Observe for data changes
        fragmentViewModel.getProductsData().observe(viewLifecycleOwner, {
            productsAdapter.addProducts(it)
        })
    }
}
