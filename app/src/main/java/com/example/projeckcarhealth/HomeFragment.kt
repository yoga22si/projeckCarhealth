package com.example.projeckcarhealth

import android.content.Intent
import android.os.Bundle
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

        // Menambahkan onClickListener ke tombol pada FragmentHomeBinding
        binding.buttonKusioner.setOnClickListener {
            // Navigasi ke fragment KusionerFragment
            val i = Intent(context, Kusioner::class.java)
            startActivity(i)
//            val action = HomeFragmentDirections.actionHomeFragmentToKusionerFragment()
//            findNavController().navigate(action)
        }
    }

    class HomeFragmentDirections {
        companion object {
            fun actionHomeFragmentToKusionerFragment(): androidx.navigation.NavDirections {
                // Mengembalikan aksi navigasi dari HomeFragment ke KusionerFragment
                return HomeFragmentDirections.actionKusionerFragment()
            }

            private fun actionKusionerFragment(): androidx.navigation.NavDirections {
                // Menggunakan Safe Args untuk membuat aksi navigasi dari HomeFragment ke KusionerFragment
                return HomeFragmentDirections.actionKusionerFragment()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
