package com.examples.mycapstoneproject.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.examples.mycapstoneproject.ui.main.MainActivity
import com.examples.mycapstoneproject.data.api.ApiConfig
import com.examples.mycapstoneproject.data.datastore.UserPreferences
import com.examples.mycapstoneproject.data.repository.AuthRepository
import com.examples.mycapstoneproject.databinding.ActivityLoginBinding
import com.examples.mycapstoneproject.ui.ViewModelFactory
import com.google.android.material.textfield.TextInputEditText

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val retrofitService = ApiConfig.getApiService()
    private lateinit var EdtEmail: TextInputEditText
    private lateinit var EdtPassword: TextInputEditText

    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        EdtEmail = binding.edEmailRegister
        EdtPassword = binding.edPwRegister
        val pref = UserPreferences.getInstance(dataStore)
        viewModel = ViewModelProvider(this,ViewModelFactory(AuthRepository(retrofitService),pref)).get(AuthViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            val edtEmail = EdtEmail.text.toString()
            val edtPw = EdtPassword.text.toString()
            when {
                edtEmail.isEmpty() -> {
                    showToast("Pastikan Semua terisi")
                }
                else ->{
                    when {
                        edtPw.isEmpty() -> {
                            showToast("Pastikan Semua terisi")
                        }
                        else -> {
                           viewModel.userLogin(edtEmail,edtPw)
                        }
                    }
                }
            }


            viewModel.login.observe(this){user->
                val token1 = user.accessToken
                viewModel.saveToken(token1)


                val token = viewModel.getToken().toString()
                if (token.isEmpty()) {
                    Toast.makeText(this, "Gagal Login", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
          
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
        }
        binding.btnLoginOption.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        setupView()


        viewModel.login.observe(this){user ->
            val token1 = user.accessToken
            viewModel.saveToken(token1)


            val token = viewModel.getToken().toString()
            if (token.isEmpty()) {
                Toast.makeText(this, "Login Terlebbih Dahulu", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
    private fun setupView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}