package com.example.firebaseauth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaseauth.MainActivity.Companion.connected
import com.example.firebaseauth.databinding.ActivitySignInBinding
import com.example.firebaseauth.models.User
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {
    private var binding: ActivitySignInBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignInBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        setUpActionBar()
    }
    //set action bar dan binding ke fungsi SignIn
    private fun setUpActionBar(){
        setSupportActionBar(binding?.toolbarSignInActivity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        }

        binding?.toolbarSignInActivity?.setNavigationOnClickListener { onBackPressed() }
        binding?.btnSignIn?.setOnClickListener {
            signInUserRegistered()
        }

    }
    //Fungsi Komunikasi dengan Firebase
    private fun signInUserRegistered(){
        val email: String = binding?.etEmail?.text.toString()
        val password: String = binding?.etPassword?.text.toString()

        if(checkString()){
            showProgressDialog("Please Wait")
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Sign In", "signInWithEmail:success")
                        Toast.makeText(
                            baseContext,
                            "Sign In Berhasil.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        connected = true
                        val user = auth.currentUser
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Sign In", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }

    //fungsi ketika berhasil loagin dan akan langsung ke halaman mainactivity lagi
    fun signInSuccess(user: User){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    //Logika ketika user input string kosong
    private fun checkString():Boolean{
        var isValid = true

        if(binding?.etEmail?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etPassword?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
}