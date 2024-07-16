package com.example.projeckcarhealth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfilFragment : Fragment() {

    private lateinit var imageViewProfile: ImageView
    private lateinit var editTextDisplayName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonUpdateProfile: Button
    private lateinit var progressBarUpdate: ProgressBar
    private lateinit var buttonLogout: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        imageViewProfile = view.findViewById(R.id.imageViewProfile)
        editTextDisplayName = view.findViewById(R.id.editTextDisplayName)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        buttonUpdateProfile = view.findViewById(R.id.buttonUpdateProfile)
        progressBarUpdate = view.findViewById(R.id.progressBarUpdate)
        buttonLogout = view.findViewById(R.id.buttonLogout)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        sharedPref = requireActivity().getSharedPreferences("UserPrefs", AppCompatActivity.MODE_PRIVATE)

        // Load user data from SharedPreferences
        loadUserData()

        buttonUpdateProfile.setOnClickListener {
            updateProfile()
        }

        buttonLogout.setOnClickListener {
            logout()
        }

        return view
    }

    private fun loadUserData() {
        val username = sharedPref.getString("username", "")
        val email = sharedPref.getString("email", "")

        editTextDisplayName.setText(username)
        editTextEmail.setText(email)
    }

    private fun updateProfile() {
        val userId = sharedPref.getString("userId", "") ?: return
        val newUsername = editTextDisplayName.text.toString()
        val newEmail = editTextEmail.text.toString()

        if (newUsername.isEmpty() || newEmail.isEmpty()) {
            Toast.makeText(requireContext(), "Nama pengguna dan email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        progressBarUpdate.visibility = View.VISIBLE

        val userMap = hashMapOf(
            "username" to newUsername,
            "email" to newEmail
        )

        firestore.collection("users").document(userId).update(userMap as Map<String, Any>)
            .addOnSuccessListener {
                progressBarUpdate.visibility = View.GONE
                Toast.makeText(requireContext(), "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show()
                // Update SharedPreferences
                with(sharedPref.edit()) {
                    putString("username", newUsername)
                    putString("email", newEmail)
                    apply()
                }
            }
            .addOnFailureListener { exception ->
                progressBarUpdate.visibility = View.GONE
                Toast.makeText(requireContext(), "Gagal memperbarui profil: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun logout() {
        auth.signOut()

        // Clear SharedPreferences
        with(sharedPref.edit()) {
            putBoolean("isLoggedIn", false)
            // Clear other user-related data if needed
            apply()
        }

        // Redirect to MainActivity
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}
