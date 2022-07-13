package com.abdl.myauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abdl.myauth.databinding.ActivityMainBinding
import com.abdl.myauth.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        with(binding) {
            val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val pass = edtPass.text.toString().trim()
            val repeatPass = edtRepeatPass.text.toString().trim()

            var isInvalidFields = false

            if (firstName.isEmpty()) {
                isInvalidFields = true
                edtFirstName.error = "Nama depan tidak boleh kosong"
            }

            if (lastName.isEmpty()) {
                isInvalidFields = true
                edtLastName.error = "Nama belakang tidak boleh kosong"
            }

            if (!isValidEmail(email)) {
                isInvalidFields = true
                edtEmail.error = "Email tidak valid"
            }

            if (email.isEmpty()) {
                isInvalidFields = true
                edtEmail.error = "Email tidak boleh kosong"
            }

            if (pass.length < 8){
                isInvalidFields = true
                edtPass.error = "Password minimal terdiri dari 8 karakter"
            }

            if (pass.isEmpty()) {
                isInvalidFields = true
                edtPass.error = "Password tidak boleh kosong"
            }

            if (repeatPass.isEmpty()){
                isInvalidFields = true
                edtRepeatPass.error = "Password tidak boleh kosong"
            }

            if (repeatPass != pass){
                isInvalidFields = true
                edtRepeatPass.error = "Password tidak boleh beda"
            }

            if (!isInvalidFields) {
                Toast.makeText(this@RegisterActivity, "Registrasi berhasil!", Toast.LENGTH_LONG).show()
                Intent(this@RegisterActivity, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}