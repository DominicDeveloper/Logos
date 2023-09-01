package com.asadbek.logos

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.asadbek.logos.adapters.AdapterSpinner
import com.asadbek.logos.databinding.ActivityFinalBinding
import com.asadbek.logos.models.CurrentImg
import com.asadbek.logos.models.LogoModul
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class FinalActivity : AppCompatActivity() {
    lateinit var binding: ActivityFinalBinding
    lateinit var list:ArrayList<LogoModul>
    lateinit var listTextStyles:ArrayList<String>
    lateinit var listStyles:ArrayList<Typeface>
    lateinit var adapterSpinner: AdapterSpinner
    var x:Float? = null
    var y:Float? = null
    var dx:Float?= null
    var dy:Float? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        list = ArrayList()
        listStyles = ArrayList()
        listTextStyles = ArrayList()

        listStyles.addAll(getAllMy())
        getImage()
        getTextStyles()

        binding.finalCardViewBlack.setOnClickListener {
            anim(binding.finalCardViewBlack)
           binding.finalEditText.setTextColor(ContextCompat.getColor(this,R.color.black))
       }
        binding.finalCardViewWhite.setOnClickListener {
            anim(binding.finalCardViewWhite)
            binding.finalEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
        }
        binding.finalCardViewRed.setOnClickListener {
            anim(binding.finalCardViewRed)
            binding.finalEditText.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
        binding.finalCardViewGreen.setOnClickListener {
            anim(binding.finalCardViewGreen)
            binding.finalEditText.setTextColor(ContextCompat.getColor(this,R.color.greeen))
        }
        binding.finalCardViewMain.setOnClickListener {
            anim(binding.finalCardViewMain)
            binding.finalEditText.setTextColor(ContextCompat.getColor(this,R.color.main))
        }


        adapterSpinner = AdapterSpinner(this,listTextStyles)
        // val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list)
        binding.spinner.adapter = adapterSpinner

        binding.spinner.onItemSelectedListener = object : 
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                binding.finalEditText.setTypeface(listStyles[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                
            }

        }

    }

    private fun getTextStyles() {
        if (listTextStyles.isEmpty()){
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
            listTextStyles.add("Your text style")
        }

    }

    private fun anim(view: View){
        val anim = AnimationUtils.loadAnimation(this,R.anim.anim_click)
        view.startAnimation(anim)
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getImage(){
        binding.finalProgress.visibility = View.VISIBLE
        binding.finalProgress.setProgress(100,true)

        Glide.with(this)
            .load(CurrentImg.imageLink)
            .addListener(object :RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                    binding.finalProgress.visibility =View.INVISIBLE

                    return false
                }
            })
            .into(binding.finalImage)


        binding.finalEditText.setOnClickListener {
            binding.finalEditText.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.borderly))
            binding.finalEditText.isCursorVisible = true
            binding.view1.visibility= View.VISIBLE
        }

        binding.finalBtnSave.setOnClickListener {
            binding.finalEditText.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.transparent))
            binding.finalEditText.isCursorVisible = false
            binding.view1.visibility= View.INVISIBLE
            newStep()
        }
    }
    private fun saveToStorage(bitmap: Bitmap) {
        val imageName = "${System.currentTimeMillis()}_logos.jpg"
        var fos : OutputStream?= null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME,imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri : Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues)
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
    private fun getImageOfView(container: CardView): Bitmap? {
        var image: Bitmap? = null
        try {
            image = Bitmap.createBitmap(container.measuredWidth,container.measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(image)
            container.draw(canvas)

        }catch (e:Exception){
            e.printStackTrace()
        }
        return image
    }
    private fun newStep(){
          val bitmap = getImageOfView(binding.finalCardView)
          if (bitmap != null){

             saveToStorage(bitmap)
         }
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event!!.action == MotionEvent.ACTION_DOWN){
            x = event.x
            y = event.y
        }

        if (event.action == MotionEvent.ACTION_MOVE){
            dx = event.x - x!!
            dy = event.y - y!!

            binding.finalEditText.x = binding.finalEditText.x + dx!!
            binding.finalEditText.y = binding.finalEditText.y + dy!!

            binding.view1.x = binding.view1.x + dx!!
            binding.view1.y = binding.view1.y + dy!!

            x = event.x
            y = event.y
        }

        return super.onTouchEvent(event)
    }
    private fun getAllMy():ArrayList<Typeface> {
        val listMw = ArrayList<Typeface>()
        listMw.add(Typeface.createFromAsset(assets,"fonts/f1.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f2.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f3.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f4.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f5.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f6.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f7.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f8.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f9.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/f10.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont1.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont2.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont3.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont4.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont5.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont6.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont7.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont8.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont9.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffont10.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt1.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt2.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt3.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt4.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt5.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt6.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt7.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt8.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt9.ttf"))
        listMw.add(Typeface.createFromAsset(assets,"fonts/ffontt10.ttf"))

        return listMw

    }

}