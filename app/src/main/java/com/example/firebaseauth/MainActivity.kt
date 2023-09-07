package com.example.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.firebaseauth.databinding.ActivityMainBinding
import com.example.firebaseauth.firebase.FireStoreClass
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        //Variable untuk get ID
        var currentID = FireStoreClass().getCurrentId()
        //Handling button ketika user belum signIn
        binding?.imgCalculator?.setOnClickListener {
            if(currentID.isNotEmpty()){
                startActivity(Intent(this, BMIActivity::class.java))
            }else{
                Toast.makeText(
                    baseContext,
                    "Sign In First.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
        //Handling button ketika user sudah signIn dan akan masuk ke halaman signIn lagi
        binding?.btSignRegis?.setOnClickListener {
            if(currentID.isNotEmpty()){
                Toast.makeText(
                    baseContext,
                    "You Already SignIn Disconnected First",
                    Toast.LENGTH_SHORT,
                ).show()
            }
            else{
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }

        //Disconnect Button
        binding?.btDiscon?.setOnClickListener {
            if (currentID.isNotEmpty()) {
//                showProgressDialog("Please Wait")
                currentID = ""
                Toast.makeText(
                    baseContext,
                    "berhasil signout",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    baseContext,
                    "You are not signed in",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    companion object {
        var connected = false
    }

}