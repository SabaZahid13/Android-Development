package com.example.bottomnavigationfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigationfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ReplaceFragment(Home())
        binding.navbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> ReplaceFragment(Home())
                R.id.contacts-> ReplaceFragment(Contacts())
                R.id.call -> ReplaceFragment(Call())
                else -> {
                }
            }
            true
        }

    }
    private fun ReplaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commit()
    }
}