package com.examples.mycapstoneproject.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.api.ApiConfig
import com.examples.mycapstoneproject.data.datastore.UserPreferences
import com.examples.mycapstoneproject.data.repository.AuthRepository
import com.examples.mycapstoneproject.databinding.ActivityLoginBinding
import com.examples.mycapstoneproject.databinding.ActivityRegisterBinding
import com.examples.mycapstoneproject.ui.ViewModelFactory
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val retrofitService = ApiConfig.getApiService()
    private var gender: String = "male"
    lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = UserPreferences.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(AuthRepository(retrofitService),pref)).get(AuthViewModel::class.java)


        setupAction()
        setupView()

    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked


            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_male ->
                    if (checked) {
                        gender = "male"
                    }
                R.id.rb_female ->
                    if (checked) {
                        gender="female"
                    }
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
    private fun setupAction() {

        binding.btnRegister.setOnClickListener{
            val name = binding.edNameRegister.text.toString()
            val email = binding.edEmailRegister.text.toString()
            val password = binding.edPwRegister.text.toString()
            val password2 = binding.edPwRegister2.text.toString()
            when {
                name.isEmpty() -> {
                    binding.inputNameRegister.error = "Masukkan email"
                }
                email.isEmpty() -> {
                    binding.inputEmailRegister.error = "Masukkan email"
                }
                password.isEmpty() ->{
                    binding.inputPwRegister.error = "Masukkan password"
                }
                password2.isEmpty() -> {
                    binding.inputPwRegister2.error = "Masukkan konfirmasi password"
                }
                else -> {
                    viewModel.userRegis(name,email,gender,password)
                    finish()
//                    val intent = Intent(this,LoginActivity::class.java)
//                    startActivity(intent)
                }
            }
        }
    }
}