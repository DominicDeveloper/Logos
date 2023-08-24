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
                        "logo_1" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(
                            ContextCompat.getDrawable(context,R.drawable.logo_1))
                        "logo_2" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_2))
                        "logo_3" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_3))
                        "logo_4" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_4))
                        "logo_5" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_5))
                        "logo_6" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_6))
                        "logo_7" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_7))
                        "logo_8" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_8))
                        "logo_9" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_9))
                        "logo_10" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_10))
                        "logo_11" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_11))
                        "logo_12" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_12))
                        "logo_13" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_13))
                        "love3" -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.love3))
                        else -> itemRv.findViewById<ImageView>(R.id.itemPartImg).setImageDrawable(ContextCompat.getDrawable(context,R.drawable.logo_1))

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