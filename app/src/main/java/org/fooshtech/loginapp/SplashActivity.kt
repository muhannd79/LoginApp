package org.fooshtech.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_splash_open.setOnClickListener {

          val  intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}