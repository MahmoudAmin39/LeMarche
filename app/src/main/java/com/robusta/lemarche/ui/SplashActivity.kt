package com.robusta.lemarche.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.robusta.lemarche.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        textView_splash_search.setOnClickListener {
            val intent = Intent(this, ProductsSearchActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}