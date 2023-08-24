package com.asadbek.logos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.asadbek.logos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val handler = Handler()
        handler.postDelayed(runnable,1000)

    }
    val runnable:Runnable = Runnable(){
        run {
            nextAct()
        }
    }
    private fun nextAct(){
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}