package com.asadbek.logos

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import com.asadbek.logos.adapters.AdapterParts
import com.asadbek.logos.databinding.ActivitySecondBinding
import com.asadbek.logos.models.CurrentImg
import com.asadbek.logos.models.Parts
import com.asadbek.logos.models.TypeOfImage
import kotlin.system.exitProcess

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var adapterParts: AdapterParts
    lateinit var list:ArrayList<Parts>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        list = ArrayList()

        loadList()
        adapterParts = AdapterParts(this,list,object : AdapterParts.RvClick{
            override fun onClick(parts: Parts) {
                if(checkInternet()){
                        TypeOfImage.type = parts.name.toString()
                        CurrentImg.typeOfImg = parts.imageToken.toString()
                        val intent= Intent(this@SecondActivity,ListActivity::class.java)
                        startActivity(intent)
                }else{
                    Toast.makeText(this@SecondActivity, "No connection! try again", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.rvList.adapter = adapterParts

    }
     private fun checkInternet(): Boolean {

             val connectivityManager =
                 this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                 val activeNetwork = connectivityManager.activeNetwork
                 val p = connectivityManager.getNetworkCapabilities(activeNetwork)
                 return p != null && p.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
             } else {
                 val activeNetworkInfo = connectivityManager.activeNetworkInfo
                 return activeNetworkInfo != null && activeNetworkInfo.isConnected
             }


         }

    private fun loadList() {
       list.add(Parts("GAMING LOGO","gaming"))
        list.add(Parts("ASSASSINS LOGO","assassin"))
        list.add(Parts("CLOWNS LOGO","clown"))
       // list.add(Parts("ESPORTS LOGO","logo_2"))
      //  list.add(Parts("FF LOGO","logo_3"))
        list.add(Parts("ANIME LOGO GIRLS","animegirls"))
        list.add(Parts("ANIME LOGO BOYS","animeboys"))
        list.add(Parts("FOR BOYS","boys19"))
        list.add(Parts("FOR GIRLS","girls1"))
        list.add(Parts("MULTI","multi1"))
        list.add(Parts("OTHER LOGO","logo9"))
        list.add(Parts("EMOJE LOGO","emoji1"))
      //  list.add(Parts("HACKER LOGO","logo_11"))
        list.add(Parts("PUBG LOGO","pubg1"))
      //  list.add(Parts("FRIDAY LOGO UZBEK","logo_13"))
        list.add(Parts("LOVE","love3"))
        list.add(Parts("FRIDAY LOGO UZBEK","friday"))
    }
    private fun sensor(){

    }


}