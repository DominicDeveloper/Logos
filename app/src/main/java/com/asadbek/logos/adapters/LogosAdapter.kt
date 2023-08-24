package com.asadbek.logos.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.asadbek.logos.R
import com.asadbek.logos.models.LogoModul
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class LogosAdapter(private val context: Context,private val list: List<LogoModul>):RecyclerView.Adapter<LogoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogoViewHolder {
        return LogoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: LogoViewHolder, position: Int) {
        val logo = list[position]
        holder.progress.visibility = View.VISIBLE
        holder.progress.setProgress(100,true)
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
            .into(holder.image)

        
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Working!", Toast.LENGTH_SHORT).show()
        }
        
        
    }


    override fun getItemCount(): Int {
        return list.size
    }


}
class LogoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val image:ImageView = itemView.findViewById(R.id.itemListImage)
    val progress:ProgressBar = itemView.findViewById(R.id.itemListProgress)

}