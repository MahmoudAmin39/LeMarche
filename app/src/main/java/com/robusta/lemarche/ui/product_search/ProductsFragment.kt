package com.robusta.lemarche.ui.product_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.robusta.lemarche.R
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private val viewModel: ProductSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSearchTermLiveData().observe(viewLifecycleOwner, { textView_query.text = it })
    }
}