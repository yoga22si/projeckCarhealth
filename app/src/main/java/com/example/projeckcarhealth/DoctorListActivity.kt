package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class DoctorListActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var doctorListContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        firestore = FirebaseFirestore.getInstance()
        doctorListContainer = findViewById(R.id.doctorListContainer)

        loadDoctors()
    }

    private fun loadDoctors() {
        firestore.collection("users")
            .whereEqualTo("userType", "doctor")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val doctorView = LayoutInflater.from(this).inflate(R.layout.item_doctor, doctorListContainer, false)

                    val doctorName = doctorView.findViewById<TextView>(R.id.doctorName)
                    val doctorSpecialization = doctorView.findViewById<TextView>(R.id.doctorSpecialization)
                    val doctorExperience = doctorView.findViewById<TextView>(R.id.doctorExperience)
                    val doctorImage = doctorView.findViewById<ImageView>(R.id.doctorImage)

                    doctorName.text = document.getString("username")
                    doctorSpecialization.text = document.getString("specialization")
                    doctorExperience.text = "Pengalaman: ${document.getString("experience")} tahun"
                    // Set doctorImage with a default image or fetch from a URL if available

                    doctorView.setOnClickListener {
                        val intent = Intent(this, ChatActivity::class.java)
                        intent.putExtra("doctorId", document.id)
                        intent.putExtra("doctorName", document.getString("username"))
                        startActivity(intent)
                    }

                    doctorListContainer.addView(doctorView)
                }
            }
    }
}
