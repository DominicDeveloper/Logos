package com.asadbek.logos.adapters

import android.content.Context
import android.graphics.Typeface
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.asadbek.logos.R

class AdapterSpinner(val context: Context,val list:ArrayList<String>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
       return list[p0]
    }

    override fun getItemId(p0: Int): Long {

        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val itemView:View
        val listMe = ArrayList<Typeface>()
        listMe.addAll(getAllMy(context))
        if (p1 == null){
            itemView = LayoutInflater.from(p2?.context).inflate(R.layout.item_spinner,p2,false)
        }else{
            itemView = p1
        }
        itemView.findViewById<TextView>(R.id.itemSpinnerTxt).text = list[p0]
        itemView.findViewById<TextView>(R.id.itemSpinnerTxt).setTypeface(listMe[p0])

        return itemView
    }

    private fun getAllMy(context: Context):ArrayList<Typeface> {
        val listMw = ArrayList<Typeface>()
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f1.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f2.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f3.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f4.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f5.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f6.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f7.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f8.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f9.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/f10.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont1.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont2.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont3.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont4.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont5.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont6.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont7.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont8.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont9.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffont10.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt1.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt2.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt3.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt4.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt5.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt6.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt7.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt8.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt9.ttf"))
        listMw.add(Typeface.createFromAsset(context.assets,"fonts/ffontt10.ttf"))

        return listMw

    }
}