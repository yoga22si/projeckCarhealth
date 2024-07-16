package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projeckcarhealth.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners
        binding.arrowImageView.setOnClickListener {
            Log.d("HomeFragment", "Arrow Image View Clicked")
            val artikelFragment = ArtikelFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.flFragment, artikelFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.btnChatConsultant.setOnClickListener {
            Log.d("HomeFragment", "Chat Consultant Button Clicked")
            val intent = Intent(activity, ConsultantActivity::class.java)
            startActivity(intent)
        }

        binding.arrowImageView.setOnClickListener {
            Log.d("HomeFragment", "View Articles Button Clicked")
            val artikelFragment = ArtikelFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.flFragment, artikelFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
