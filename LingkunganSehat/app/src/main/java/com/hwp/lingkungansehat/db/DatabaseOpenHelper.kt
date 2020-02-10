package com.hwp.lingkungansehat.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hwp.lingkungansehat.sakit.Penyakit
import com.hwp.lingkungansehat.tips.Tips
import org.jetbrains.anko.db.*

private const val DATABASE_VERSION = 1

open class DatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {

        private var instance: DatabaseOpenHelper? = null
        internal const val DATABASE_NAME = "sehat_bugar.db"

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }


    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(Penyakit.TABLE_PENYAKIT, true,
                Penyakit.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Penyakit.NAMA to TEXT + UNIQUE,
                Penyakit.KETERANGAN to  TEXT,
                Penyakit.PERAWATAN to TEXT,
                Penyakit.PENCEGAHAN to TEXT,
                Penyakit.GEJALA to TEXT,
                Penyakit.DOKTER to TEXT,
                Penyakit.SOURCE to TEXT,
                Penyakit.JENIS to INTEGER)

        db.createTable(Tips.TABLE_TIPS, true,
                Tips.ID to INTEGER + PRIMARY_KEY+ AUTOINCREMENT,
                Tips.TITLE to TEXT,
                Tips.IMG_LINK to TEXT,
                Tips.WEB_LINK to TEXT)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Tips.TABLE_TIPS, true)
        db.dropTable(Penyakit.TABLE_PENYAKIT, true)
    }
}

// Access property for Context
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)