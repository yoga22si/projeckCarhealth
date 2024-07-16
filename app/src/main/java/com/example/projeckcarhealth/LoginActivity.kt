package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        etEmail = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.signInButton)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (email.endsWith("@gmail.com")) {
                    login(email, password)
                } else {
                    Toast.makeText(this, "Email harus menggunakan domain @gmail.com", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Email atau password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        val userId = user.uid
                        Log.d("LoginActivity", "Login berhasil, UID: $userId")

                        firestore.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                if (document.exists()) {
                                    val username = document.getString("username") ?: ""
                                    val email = document.getString("email") ?: ""
                                    val userType = document.getString("userType") ?: ""
                                    Log.d("LoginActivity", "Data pengguna ditemukan, userType: $userType")

                                    // Simpan informasi pengguna ke SharedPreferences
                                    val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                                    with(sharedPref.edit()) {
                                        putString("userId", userId)
                                        putString("username", username)
                                        putString("email", email)
                                        putString("userType", userType)
                                        putBoolean("isLoggedIn", true)
                                        apply()
                                    }

                                    // Start BerandaActivity
                                    startActivity(Intent(this, BerandaActivity::class.java))
                                    finish()
                                } else {
                                    Log.d("LoginActivity", "Data pengguna tidak ditemukan di Firestore")
                                    Toast.makeText(applicationContext, "Data pengguna tidak ditemukan", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .addOnFailureListener { exception ->
                                Log.e("LoginActivity", "Gagal mendapatkan data pengguna dari Firestore", exception)
                                Toast.makeText(applicationContext, "Gagal mendapatkan data pengguna: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Log.d("LoginActivity", "Pengguna tidak ditemukan setelah login berhasil")
                        Toast.makeText(applicationContext, "Pengguna tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    task.exception?.let { exception ->
                        Log.e("LoginActivity", "Login gagal", exception)
                        Toast.makeText(applicationContext, "Login gagal: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}
