package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var userTypeSpinner: Spinner
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        etUsername = findViewById(R.id.Username)
        etEmail = findViewById(R.id.Email)
        etPassword = findViewById(R.id.Password)
        btnRegister = findViewById(R.id.registerButton)
        userTypeSpinner = findViewById(R.id.userTypeSpinner)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val userType = userTypeSpinner.selectedItem.toString()

            // Validasi input
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show()
            } else if (!email.endsWith("@gmail.com")) {
                Toast.makeText(applicationContext, "Email harus menggunakan domain @gmail.com", Toast.LENGTH_SHORT).show()
            } else {
                // Daftar pengguna dengan Firebase Authentication
                registerUser(username, email, password, userType)
            }
        }
    }

    private fun registerUser(username: String, email: String, password: String, userType: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        val userId = user.uid
                        val userMap = hashMapOf(
                            "username" to username,
                            "email" to email,
                            "userType" to userType
                        )

                        // Simpan data pengguna di Firestore
                        firestore.collection("users").document(userId).set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(applicationContext, "Registrasi berhasil. Silakan login.", Toast.LENGTH_SHORT).show()
                                auth.signOut()

                                // Arahkan pengguna ke halaman login
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener { exception ->
                                Log.e("RegisterActivity", "Gagal menyimpan data pengguna", exception)
                                Toast.makeText(applicationContext, "Gagal menyimpan data pengguna: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    task.exception?.let { exception ->
                        Log.e("RegisterActivity", "Registrasi gagal: ", exception)
                        Toast.makeText(applicationContext, "Registrasi gagal: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}
