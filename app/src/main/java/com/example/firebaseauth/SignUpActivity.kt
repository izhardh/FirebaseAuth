package com.example.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivitySignUpBinding
import com.example.firebaseauth.firebase.FireStoreClass
import com.example.firebaseauth.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : BaseActivity() {
    private var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        super.onCreate(savedInstanceState)

        setUpActionBar()

        binding?.btnSignUp?.setOnClickListener {
            register()
        }
    }

    //set action bar dan binding ke fungsi SignUp
    private fun setUpActionBar(){
        setSupportActionBar(binding?.toolbarSignUpActivity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        }

        binding?.toolbarSignUpActivity?.setNavigationOnClickListener { onBackPressed() }

    }
    //Logika ketika user input string kosong
    private fun checkString():Boolean{
        var isValid = true

        if(binding?.etName?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etEmail?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etPassword?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
    //Fungsi Komunikasi dengan Firebase
    private fun register(){
        val name: String = binding?.etName?.text.toString()
        val email: String = binding?.etEmail?.text.toString()
        val password: String = binding?.etPassword?.text.toString()


        if(checkString()){
            showProgressDialog("Please Wait")
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registerEmail = firebaseUser.email!!
                    val user = User(firebaseUser.uid,name,registerEmail)
                    FireStoreClass().registerUser(this, user)
                }else{
                    Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this, "Input Data dengan Benar", Toast.LENGTH_LONG).show()
        }
    }
    //fungsi ketika berhasil signup dan akan langsung ke halaman menu lagi
    fun userRegiseteredSucsess(){
        Toast.makeText(
            this,
            "Registrasi telah berhasil",
            Toast.LENGTH_LONG
        ).show()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

}