package com.asadbek.logos

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.asadbek.logos.adapters.LogosAdapter
import com.asadbek.logos.databinding.ActivityListBinding
import com.asadbek.logos.models.LogoModul
import com.asadbek.logos.models.TypeOfImage
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

const val TAG = "MainAct"
class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var list:ArrayList<LogoModul>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        list = ArrayList()

        getImagesFromServer()


     binding.rvList2.apply {
         layoutManager = LinearLayoutManager(this@ListActivity)
     }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getImagesFromServer() {
        binding.progress.visibility = View.VISIBLE
        binding.progress.setProgress(100,true)
       FirebaseFirestore.getInstance().collection(getType())
           .get()
           .addOnSuccessListener { logos ->
               for (logo in logos){
                   val logoModul = logos.toObjects(LogoModul::class.java)
                   binding.rvList2.adapter = LogosAdapter(this,logoModul)
               }
               binding.progress.visibility = View.GONE
           }



    }
    private fun getType():String{
        when(TypeOfImage.type){
            "GAMING LOGO" -> return "gaming"
            "ESPORTS LOGO" -> return "esports"
            "FF LOGO" -> return "ff"
            "ANIME LOGO GIRLS" -> return "animegirls"
            "ANIME LOGO BOYS" -> return "animeboys"
            "FOR BOYS" -> return "boys"
            "FOR GIRLS" -> return "girls"
            "MULTI" -> return "multi"
            "OTHER LOGO" -> return "other"
            "EMOJE LOGO" -> return "emoje"
            "HACKER LOGO" -> return "hacker"
            "PUBG LOGO" -> return "pubg"
            "FRIDAY LOGO UZBEK" -> return "friday"
            "LOVE" -> return "love"
            else -> return "empty"
        }
    }

    private fun newStep(){
      //  val bitmap = getImageOfView(binding.container)
       // if (bitmap != null){
       //     saveToStorage(bitmap)
       // }
    }

    private fun saveToStorage(bitmap: Bitmap) {
        val imageName = "${System.currentTimeMillis()}_logos.jpg"
        var fos :OutputStream?= null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply { 
                    put(MediaStore.MediaColumns.DISPLAY_NAME,imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES)
                }
                val imageUri :Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues)
                fos = imageUri?.let { 
                    resolver.openOutputStream(it)
                }
            }
        }
        else{
            val imageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imageDirectory,imageName)
            fos = FileOutputStream(image)
        }
        fos?.use { 
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,it)
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageOfView(container: ImageView): Bitmap? {
        var image:Bitmap? = null
        try {
            image = Bitmap.createBitmap(container.measuredWidth,container.measuredHeight,Bitmap.Config.ARGB_8888)
            val canvas = Canvas(image)
            container.draw(canvas)
            
        }catch (e:Exception){
            e.printStackTrace()
        }
        return image
    }


}