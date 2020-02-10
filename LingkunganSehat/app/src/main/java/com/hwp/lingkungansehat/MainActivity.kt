package com.hwp.lingkungansehat

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.hwp.lingkungansehat.db.DatabaseOpenHelper
import com.hwp.lingkungansehat.db.database
import com.hwp.lingkungansehat.sakit.SakitFragment
import com.hwp.lingkungansehat.tips.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.defaultSharedPreferences
import java.io.FileOutputStream
import com.hwp.lingkungansehat.hidinker.MapsActivity


class MainActivity : AppCompatActivity(), OnFragmentChange {

    private val IS_FIRST : String = "isFirst"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val a = supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit, R.anim.abc_popup_exit, R.anim.abc_popup_enter)

        when (item.itemId) {
            R.id.navigation_home -> {
                a.replace(R.id.main_container, HomeFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                a.replace(R.id.main_container, SakitFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                startActivity(Intent(this, MapsActivity::class.java))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val isFirst = defaultSharedPreferences.getBoolean(IS_FIRST,true)
        if (isFirst){
            database.use {  }
            initDatabase()
        }
    }

    private fun initDatabase() {
        try {
            val currentDB = this.getDatabasePath(DatabaseOpenHelper.DATABASE_NAME)
            assets.open("sehat_bugar.db").copyTo(FileOutputStream(currentDB))
        } catch (e: Exception) {
            Toast.makeText(this, "Exception $e", Toast.LENGTH_SHORT).show()
        }
    }

    override fun changeFragment(targetFragment: Fragment, addToBackStack : Boolean) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_popup_enter, R.anim.abc_popup_exit
                        , R.anim.abc_popup_enter, R.anim.abc_popup_exit)
                .replace(R.id.main_container, targetFragment)
                .addToBackStack(null)
                .commit()
    }


}
