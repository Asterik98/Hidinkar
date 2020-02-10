package com.hwp.lingkungansehat

import android.support.v4.app.Fragment


interface OnFragmentChange {
    fun changeFragment(targetFragment: Fragment, addToBackStack : Boolean = false)
}