package com.hwp.lingkungansehat.sakit

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hwp.lingkungansehat.OnFragmentChange
import com.hwp.lingkungansehat.R
import kotlinx.android.synthetic.main.fragment_sakit.*

class SakitFragment : Fragment(), View.OnClickListener {
    private var listener: OnFragmentChange? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sakit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hipertensi_chip.setOnClickListener(this)
        diabetes_chip.setOnClickListener(this)
        kanker_chip.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.hipertensi_chip ->{
                listener?.changeFragment(DetailPenyakitFragment.newInstance(1), true)
            }
            R.id.diabetes_chip ->{
                listener?.changeFragment(DetailPenyakitFragment.newInstance(2), true)
            }
            R.id.kanker_chip ->{
                listener?.changeFragment(DetailPenyakitFragment.newInstance(3), true)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                SakitFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentChange) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
