package com.robusta.lemarche.ui.product_search

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.robusta.lemarche.R
import com.robusta.lemarche.ui.products.ProductsFragment

class ProductsSearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val viewModel: ProductSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_search)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout_fragment_container, ProductsFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.item_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                searchView.setQuery("", true)
                hideKeyboard()
                return true
            }
        })
        return true
    }

    private fun hideKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_search && item.actionView is SearchView) {
            (item.actionView as SearchView).onActionViewExpanded()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val finalQuery = query ?: ""
        viewModel.onQueryTextSubmit(finalQuery)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}