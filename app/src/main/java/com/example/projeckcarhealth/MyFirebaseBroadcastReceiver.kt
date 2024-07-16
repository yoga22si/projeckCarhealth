package com.example.projeckcarhealth

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyFirebaseBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Tangani tugas penerima broadcast di sini
        Toast.makeText(context, "Notifikasi Diterima!", Toast.LENGTH_SHORT).show()
    }
}
