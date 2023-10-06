package com.asadbek.logos

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.asadbek.logos.adapters.AdapterParts
import com.asadbek.logos.databinding.ActivitySecondBinding
import com.asadbek.logos.models.CurrentImg
import com.asadbek.logos.models.Parts
import com.asadbek.logos.models.TypeOfImage
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

const val ADS = "ca-app-pub-3426494742122444/3816409391"
private var mInterstitialAd: InterstitialAd? = null
class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var adapterParts: AdapterParts

    lateinit var list:ArrayList<Parts>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
     //   supportActionBar?.hide()
        list = ArrayList()

        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, INTERSTIAL,adRequest,object: InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(p0: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(p0: InterstitialAd) {
                mInterstitialAd = p0

            }
        })

        MyAsync().execute()

        adapterParts = AdapterParts(this,list,object : AdapterParts.RvClick{
            override fun onClick(parts: Parts) {
                if(checkInternet()){
                        TypeOfImage.type = parts.name.toString()
                        CurrentImg.typeOfImg = parts.imageToken.toString()
                        if (mInterstitialAd != null){
                            mInterstitialAd?.show(this@SecondActivity)
                        }else{
                            // no ads loaded!
                        }
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
        list.add(Parts("HACKERS LOGO","hacker"))
        list.add(Parts("PUBG LOGO","pubg1"))
        list.add(Parts("LOVE","love3"))
        list.add(Parts("FRIDAY LOGO UZBEK","friday"))
    }
    inner class MyAsync:AsyncTask<Void,Void,Void?>(){
        @Deprecated("Deprecated in Java")
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onPreExecute() {
            super.onPreExecute()
            // show a progress bar
            binding.secondProgress.visibility = View.VISIBLE
            binding.secondProgress.setProgress(100,true)
        }
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): Void? {
            loadList()

            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            binding.secondProgress.visibility = View.INVISIBLE
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.aboutUs -> sharing("https://instagram.com/azimov_development")
            R.id.rateUp -> {
               rateApp("https://play.google.com/store/apps/details?id=com.asadbek.logos")
            }
            R.id.otherApps -> {
                rateApp("https://play.google.com/store/search?q=dominicdeveloper&c=apps")
            }

        }

        return true
    }
    private fun sharing(uri:String){
        val uri : Uri = Uri.parse(uri)
        val myAppLink = Intent(Intent.ACTION_VIEW,uri)
        try {
            startActivity(myAppLink)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    private fun rateApp(url:String){
        val uri:Uri = Uri.parse(url)
        val goToMarket = Intent(Intent.ACTION_VIEW,uri)
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            startActivity(goToMarket)

        }catch (e: ActivityNotFoundException){
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }


}