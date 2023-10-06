package com.asadbek.logos.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.asadbek.logos.R
import com.asadbek.logos.models.CurrentImg
import com.asadbek.logos.models.LogoModul
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.button.MaterialButton
import com.google.android.material.radiobutton.MaterialRadioButton
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class LogosAdapter(private val activity: Activity,private val context: Context,private val list: List<LogoModul>,val rvClick: onRvClick):RecyclerView.Adapter<LogoViewHolder>() {
    var dialog:Dialog? = Dialog(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogoViewHolder {
        return LogoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false))
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: LogoViewHolder, position: Int) {
        val logo = list[position]
        holder.progress.visibility = View.VISIBLE
        holder.progress.setProgress(100,true)
        when(CurrentImg.typeOfImg){
            "boys19" -> {
                holder.textForLE.visibility = View.GONE
                holder.textForBGML.visibility = View.VISIBLE
            }
            "girls1" -> {
                holder.textForLE.visibility = View.GONE
                holder.textForBGML.visibility = View.VISIBLE
            }
            "emoji1" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "logo9" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "multi1" -> {
                holder.textForLE.visibility = View.GONE
                holder.textForBGML.visibility = View.VISIBLE
            }
            "pubg1" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "love3" -> {
                holder.textForLE.visibility = View.GONE
                holder.textForBGML.visibility = View.VISIBLE
            }
            "gaming" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "assassin" ->{
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "clown" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "friday" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "animeboys" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "animegirls" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
            "hacker" -> {
                holder.textForLE.visibility = View.VISIBLE
                holder.textForBGML.visibility = View.GONE
            }
        }
        holder.textForLE.text = "Your nickname"
        holder.textForBGML.text = "Your nickname"
        Glide.with(context)
            .load(logo.imageLink)
            .addListener(object :RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {

                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    holder.progress.visibility = View.GONE
                    return false
                }
            })
            .into(holder.img)

        
        holder.itemView.setOnClickListener {
            when(CurrentImg.typeOfImg){
                "boys19" -> {
                    holder.textForLE.visibility = View.INVISIBLE
                    holder.textForBGML.visibility = View.VISIBLE
                    showDialog(holder.image,holder.textForBGML,holder.textForLE)
                }
                "girls1" -> {
                    holder.textForLE.visibility = View.INVISIBLE
                    holder.textForBGML.visibility = View.VISIBLE
                    showDialog(holder.image,holder.textForBGML,holder.textForLE)
                }
                "multi1" -> {
                    holder.textForLE.visibility = View.INVISIBLE
                    holder.textForBGML.visibility = View.VISIBLE
                    showDialog(holder.image,holder.textForBGML,holder.textForLE)
                }
                "love3" -> {
                    holder.textForLE.visibility = View.INVISIBLE
                    holder.textForBGML.visibility = View.VISIBLE
                    showDialog(holder.image,holder.textForBGML,holder.textForLE)
                }
                else -> {
                    rvClick.onClick(logo)
                }
            }
        }
        
        
    }




    private fun showDialog(image: CardView, text: TextView,text2:TextView) {
        dialog!!.setContentView(R.layout.item_dialog)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val nickname:EditText = dialog!!.findViewById(R.id.itemDialogEditText)
        val btn:MaterialButton = dialog!!.findViewById(R.id.itemDialogBtnSave)
        val modeBlack:MaterialRadioButton = dialog!!.findViewById(R.id.itemDialogRadioButtonBlack)
        val modeWhite:MaterialRadioButton = dialog!!.findViewById(R.id.itemDialogRadioButtonWhite)

        modeBlack.setOnCheckedChangeListener { compoundButton, b -> 
            if (b){
                text2.setTextColor(ContextCompat.getColor(context,R.color.black))
                text.setTextColor(ContextCompat.getColor(context,R.color.black))
                modeWhite.isChecked = false
            }
        }
        modeWhite.setOnCheckedChangeListener { compoundButton, b -> 
            if (b){
                text2.setTextColor(ContextCompat.getColor(context,R.color.white))
                text.setTextColor(ContextCompat.getColor(context,R.color.white))
                modeBlack.isChecked = false
            }
        }
        btn.setOnClickListener {
            if (nickname.text.isNotEmpty()){
                text.text = nickname.text.toString().uppercase()
                text2.text = nickname.text.toString().uppercase()
                var bitmap = getImageOfView(image)
                if (modeWhite.isChecked || modeBlack.isChecked){
                    if (bitmap != null){
                        saveToStorage(bitmap)
                        nickname.text.clear()
                        dialog!!.cancel()
                    }
                }else{
                    Toast.makeText(context, "Please, choose color!", Toast.LENGTH_SHORT).show()
                }
                 
            }
        }

        dialog!!.show()
    }



    private fun saveToStorage(bitmap: Bitmap) {
        val imageName = "${System.currentTimeMillis()}_logos.jpg"
        var fos : OutputStream?= null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            context.contentResolver?.also { resolver ->
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
            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageOfView(container: CardView): Bitmap? {
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

    override fun getItemCount(): Int {
        return list.size
    }
    interface onRvClick{
        fun onClick(logoModul: LogoModul)
    }

}
class LogoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val image:CardView = itemView.findViewById(R.id.itemListImage)
    val progress:ProgressBar = itemView.findViewById(R.id.itemListProgress)
    val textForBGML:TextView = itemView.findViewById(R.id.itemListTextForBoysGirlsMultiLove)
    val textForLE:TextView = itemView.findViewById(R.id.itemListTextForLogoEmoji)
    val img:ImageView = itemView.findViewById(R.id.img)

}