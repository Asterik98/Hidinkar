package com.hwp.lingkungansehat.sakit

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hwp.lingkungansehat.R
import com.squareup.picasso.Picasso

class PenyakitAdapter(context: Context?, private val penyakitList: List<Penyakit>, private val listener: (Penyakit) -> Unit) : RecyclerView.Adapter<PenyakitAdapter.PenyakitViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenyakitViewHolder {
        return PenyakitViewHolder(inflater.inflate(R.layout.penyakit_listitem, parent, false))
    }

    override fun getItemCount()= penyakitList.size

    override fun onBindViewHolder(holder: PenyakitViewHolder, position: Int) {
        holder.bindItem(penyakitList[position], listener)
    }

    class PenyakitViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val nama: TextView = view.findViewById(R.id.nama_penyakit_textview)

        fun bindItem(penyakit: Penyakit, listener: (Penyakit) -> Unit) {
            nama.text = penyakit.nama
            when(penyakit.jenis){
                1-> nama.setTextColor(Color.WHITE)
                2-> nama.setTextColor(Color.BLACK)
                3-> nama.setTextColor(ContextCompat.getColor(itemView.context,android.R.color.holo_orange_dark))
            }
            itemView.setOnClickListener{
                listener(penyakit)
            }
        }
    }
}

