package com.hwp.lingkungansehat.tips

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.hwp.lingkungansehat.R
import com.squareup.picasso.Picasso

class TipsAdapter(context: Context?, private val tipsList: List<Tips>, private val listener: (Tips) -> Unit) : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder {
        return TipsViewHolder(inflater.inflate(R.layout.tips_cardview, parent, false))
    }

    override fun getItemCount()= tipsList.size

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) {
        holder.bindItem(tipsList[position], listener)
    }

    class TipsViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val title: TextView = view.findViewById(R.id.tips_title)
        private val image: ImageView = view.findViewById(R.id.tips_imageview)
        private val button: Button = view.findViewById(R.id.detail_button)

        fun bindItem(tips: Tips, listener: (Tips) -> Unit) {
            title.text = tips.title
            button.setOnClickListener{
                listener(tips)
            }
            Picasso.get().load(tips.img_link).into(image)
        }
    }
}

