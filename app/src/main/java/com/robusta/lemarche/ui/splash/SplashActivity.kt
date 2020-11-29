package com.robusta.lemarche.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.robusta.lemarche.R
import com.robusta.lemarche.ui.product_search.ProductsSearchActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel.fetchProducts()

        splashViewModel.getProgressBarVisibility().observe(this, { progressBar.visibility = it })
        splashViewModel.getTextViewVisibility()
            .observe(this, { textView_splash_search.visibility = it })

        splashViewModel.getTextViewTextData()
            .observe(this, {
                textView_splash_search.text = getString(it)
                if (it == R.string.msg_search) {
                    textView_splash_search.requestFocus()
                    textView_splash_search.setOnClickListener {
                        val intent = Intent(this, ProductsSearchActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    }
                }
            })
    }
}