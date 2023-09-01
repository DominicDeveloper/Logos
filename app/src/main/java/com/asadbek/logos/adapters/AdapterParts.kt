package com.asadbek.logos.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.asadbek.logos.R
import com.asadbek.logos.models.Parts

class AdapterParts(val context: Context, val list:ArrayList<Parts>, val rvClick: RvClick) : RecyclerView.Adapter<AdapterParts.VH>() {
            inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
                @SuppressLint("SetTextI18n")
                fun onBind(parts: Parts){
                    // item surati
                    when(parts.imageToken) {
                        "boys19" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.boys19))
                        "girls1" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.girls1))
                        "emoji1" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.emoji1))
                        "multi1" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.multi1))
                        "pubg1" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.pubg1))
                        "love3" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.love3))
                        "logo9" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo9))
                        "gaming" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.gaming))
                        "assassin" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.assassians5))
                        "clown" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.clown1))
                        "friday" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.juma))
                        "animeboys" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.animeboys23))
                        "animegirls" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.animegirls20))
                        else -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_broken_image_24))

                    }
                    itemRv.findViewById<TextView>(R.id.itemPartText).text = parts.name!!.uppercase()
                    itemRv.setOnClickListener {
                        rvClick.onClick(parts)
                    }
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
                return  VH(LayoutInflater.from(parent.context).inflate(R.layout.item_part,parent,false))
            }

            override fun onBindViewHolder(holder: VH, position: Int) {
                holder.onBind(list[position])
            }

            override fun getItemCount(): Int {
                return  list.size
            }
            interface RvClick{
                fun onClick(parts: Parts)

            }

}