package com.hwp.lingkungansehat.db

import android.content.Context
import com.hwp.lingkungansehat.sakit.Penyakit
import com.hwp.lingkungansehat.tips.Tips
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class DatabaseController(private val context: Context) : DatabaseOpenHelper(context) {

    fun readTips(): List<Tips>{
        val hasil = mutableListOf<Tips>()
        context.database.use {
            val result = select(Tips.TABLE_TIPS).orderBy(Tips.ID, SqlOrderDirection.ASC)
            val tips = result.parseList(classParser<Tips>())
            hasil.addAll(tips)
        }
        return hasil
    }

    fun readPenyakit(jenisPenyakit : Int?): List<Penyakit>{
        val hasil = mutableListOf<Penyakit>()
        context.database.use {
            val result = select(Penyakit.TABLE_PENYAKIT)
                    .whereSimple("${Penyakit.JENIS} = ?", jenisPenyakit.toString())
                    .orderBy(Penyakit.NAMA, SqlOrderDirection.ASC)
            val penyakit = result.parseList(classParser<Penyakit>())
            hasil.addAll(penyakit)
        }
        return hasil
    }

    fun readPenyakitDetail(idPenyakit : Int?) : Penyakit?{
        var hasil : Penyakit? = null
        context.database.use {
            val result = select(Penyakit.TABLE_PENYAKIT)
                    .whereSimple("${Penyakit.ID} = ?", idPenyakit.toString())
                    .orderBy(Penyakit.NAMA, SqlOrderDirection.ASC)
            val penyakit = result.parseSingle(classParser<Penyakit>())
            hasil = penyakit
        }
        return hasil
    }
}