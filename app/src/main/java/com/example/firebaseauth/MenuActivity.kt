package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.ActivityMenuBinding
import com.example.firebaseauth.databinding.ActivitySignUpBinding

class MenuActivity : AppCompatActivity() {
    private var binding: ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)


        //binding ketiap halaman
        binding?.btnSignUpIntro?.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
        binding?.btnSignInIntro?.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
            finish()
        }
    }
}