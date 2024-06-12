package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        etUsername = findViewById(R.id.Username)
        etEmail = findViewById(R.id.Email)
        etPassword = findViewById(R.id.Password)
        btnRegister = findViewById(R.id.registerButton)
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://projeckcarhealth-login-default-rtdb.firebaseio.com/")

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Contoh sederhana, hanya mencetak username, email, dan password ke konsol
            println("Username: $username, Email: $email, Password: $password")

            // Validasi input
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan data ke Firebase Realtime Database
                saveUserData(username, email, password)

                // Intent untuk berpindah ke halaman lain setelah registrasi
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveUserData(username: String, email: String, password: String) {
        // Simpan data pengguna ke Firebase Realtime Database
        val userData = UserData(username, email, password)
        database.child("users").child(username).setValue(userData)
    }
}

// Model data untuk menyimpan informasi pengguna
data class UserData(val username: String, val email: String, val password: String)
