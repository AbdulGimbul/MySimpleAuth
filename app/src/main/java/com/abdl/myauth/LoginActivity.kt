package com.abdl.myauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.abdl.myauth.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.lupaPassword.setOnClickListener {
            Snackbar.make(binding.container, "Silahkan menghubungi admin!", Snackbar.LENGTH_LONG)
                .also { snackbar ->
                    snackbar.setAction("Ok") {
                        snackbar.dismiss()
                    }
                }.show()
        }

    }
    private fun loginUser() {
        with(binding) {
            val username = edtUsername.text.toString().trim()
            val password = edtPass.text.toString().trim()

            var isInvalidFields = false

            if (!isValidEmail(username)) {
                isInvalidFields = true
                edtUsername.error = "Email tidak valid"
            }

            if (username.isEmpty()) {
                isInvalidFields = true
                edtUsername.error = "Email tidak boleh kosong"
            }

            if (password.length < 8){
                isInvalidFields = true
                edtPass.error = "Password minimal terdiri dari 8 karakter"
            }

            if (password.isEmpty()) {
                isInvalidFields = true
                edtPass.error = "Password tidak boleh kosong"
            }

            if (!isInvalidFields) {
                Intent(this@LoginActivity, MainActivity::class.java).also {
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