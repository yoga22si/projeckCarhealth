package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projeckcarhealth.databinding.ActivityConsultantBinding

class ConsultantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConsultantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener untuk setiap konsultan
        binding.consultant1.setOnClickListener {
            openChat("Dr. John Doe")
        }

        binding.consultant2.setOnClickListener {
            openChat("Dr. Jane Smith")
        }

        binding.consultant3.setOnClickListener {
            openChat("Dr. Emily Johnson")
        }

        binding.consultant4.setOnClickListener {
            openChat("Dr. Michael Brown")
        }

        binding.consultant5.setOnClickListener {
            openChat("Dr. Sarah Wilson")
        }

        binding.consultant6.setOnClickListener {
            openChat("Dr. William Taylor")
        }

        binding.consultant7.setOnClickListener {
            openChat("Dr. Olivia Martinez")
        }
    }

    private fun openChat(consultantName: String) {
        val intent = Intent(this, ChatActivity::class.java).apply {
            putExtra("CONSULTANT_NAME", consultantName)
        }
        startActivity(intent)
    }
}
