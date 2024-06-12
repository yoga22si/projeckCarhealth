package com.example.projeckcarhealth
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class KusionerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.kusioner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Temukan tombol di layout fragment_kusioner
        val buttonSubmit = view.findViewById<Button>(R.id.buttonSubmitKusioner)

        // Atur listener klik untuk tombol
        buttonSubmit.setOnClickListener {
            // Tambahkan logika yang sesuai ketika tombol diklik
            Toast.makeText(requireContext(), "Kusioner telah disubmit", Toast.LENGTH_SHORT).show()
        }
    }
}
