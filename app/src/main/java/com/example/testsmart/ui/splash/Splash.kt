package com.example.testsmart.ui.splash

import android.content.Intent
import android.content.pm.PackageInfo
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testsmart.R
import com.example.testsmart.presentation.UserViewModel
import com.example.testsmart.ui.MainActivity
import com.example.testsmart.ui.RegisterActivity

class Splash: AppCompatActivity() {

    lateinit var handler: Handler
    lateinit var textView: TextView
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen3)

        var pInfo: PackageInfo = this.packageManager.getPackageInfo(packageName,0)
        var version: String = pInfo.versionName
        handler = Handler()
        textView = findViewById(R.id.version)
        textView.text = "Versión: ".plus(version)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(this, Observer { user ->
            when(user.size){
                0 -> {
                    handler.postDelayed({
                        val intent = Intent(this, RegisterActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 3000)// delay e seconds to open Activity
                }
                else -> {
                    handler.postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }, 3000)// delay e seconds to open Activity
                }
            }
        })
    }
}

