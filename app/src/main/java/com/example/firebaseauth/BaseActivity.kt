package com.example.firebaseauth

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauth.databinding.DialogProgressBinding
import com.example.firebaseauth.databinding.ExitDialogButtonBinding
import com.google.firebase.auth.FirebaseAuth

//Base dibuat agar fungsi dalam class ini dapat diaksess activity manapun (yang inheritance)
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Fungsi ketika back button ditekan
    private fun customDialogButton() {
        val customDialog = Dialog(this)
        val dialogBinding = ExitDialogButtonBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        //Buat kalau hanya bisa milih dari button
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener {
            this@BaseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }


    //Fungsi ketika back button ditekan
    override fun onBackPressed() {
        customDialogButton()
//        super.onBackPressed()
    }

    //fungsi menampilkan dialog
    fun showProgressDialog(text: String) {
        val mProgressDialog = Dialog(this)
        val progDialogBinding = DialogProgressBinding.inflate(layoutInflater)
        mProgressDialog.setContentView(progDialogBinding.root)

        //Start the dialog and display it on screen.
        mProgressDialog.show()
    }
}