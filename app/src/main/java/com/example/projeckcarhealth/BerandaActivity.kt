package com.example.projeckcarhealth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BerandaActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    private val homeFragment = HomeFragment()
    private val menuFragment = MenuFragment()
    private val pesanFragment = PesanFragment()
    private val profilFragment = ProfilFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.beranda)

        bottomNavigationView = findViewById(R.id.bottomView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    replaceFragment(menuFragment)
                    true
                }
                R.id.pesan -> {
                    replaceFragment(pesanFragment)
                    true
                }
                R.id.profil -> {
                    replaceFragment(profilFragment)
                    true
                }
                R.id.home -> {
                    replaceFragment(homeFragment)
                    true
                }
                else -> {
                    // Handle unknown item ID
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }

        // Set default fragment
        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment).commit()
    }
}
