package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        etUsername = findViewById(R.id.email)
        etPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.signInButton)
        database = FirebaseDatabase.getInstance().getReference("users")

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Username atau password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(username: String, password: String) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(username).exists()) {
                    val storedPassword = dataSnapshot.child(username).child("password").getValue(String::class.java)
                    if (storedPassword == password) {
                        Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, BerandaActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Password salah", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "Username tidak terdaftar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "Login gagal: " + databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
