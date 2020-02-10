package com.hwp.lingkungansehat.sakit


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hwp.lingkungansehat.R
import com.hwp.lingkungansehat.db.DatabaseController
import kotlinx.android.synthetic.main.fragment_selected_penyakit.*


private const val ARG_PARAM1 = "param1"
class SelectedPenyakitFragment : Fragment() {

    private var idPenyakit :Int? = null
    private var penyakit: Penyakit? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_penyakit, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPenyakit = it.getInt(ARG_PARAM1)
        }
        penyakit = context?.let { DatabaseController(it).readPenyakitDetail(idPenyakit) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(penyakit?.jenis){
            1->{
                context?.let { detail_penyakit_cardview.setCardBackgroundColor(ContextCompat.getColor(it, R.color.hipertensi)) }
            }
            2->{
                context?.let { detail_penyakit_cardview.setCardBackgroundColor(ContextCompat.getColor(it, R.color.diabetes)) }
            }
            3->{
                context?.let { detail_penyakit_cardview.setCardBackgroundColor(ContextCompat.getColor(it, R.color.kanker)) }
            }
        }
        jenis_penyakit_label.text = penyakit?.nama
        perawatan_penyakit_textview.text = penyakit?.perawatan
        pencegahan_penyakit_textview.text = penyakit?.pencegahan
        keterangan_penyakit_textview.text = penyakit?.keterangan
        dokter_penyakit_textview.text = penyakit?.dokter
        gejala_penyakit_textview.text = penyakit?.gejala

    }

    companion object {
        fun newInstance(idPenyakit : Int)=
                SelectedPenyakitFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PARAM1, idPenyakit)
                    }
                }

    }
}
