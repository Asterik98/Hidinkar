package com.hwp.lingkungansehat.sakit


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hwp.lingkungansehat.OnFragmentChange
import com.hwp.lingkungansehat.R
import com.hwp.lingkungansehat.db.DatabaseController
import kotlinx.android.synthetic.main.fragment_detail_penyakit.*

private const val ARG_JENIS_PENYAKIT = "param1"
class DetailPenyakitFragment : Fragment() {

    private var jenisPenyakit : Int? = null
    private lateinit var controllerDatabase : DatabaseController
    private var listener: OnFragmentChange? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            jenisPenyakit = it.getInt(ARG_JENIS_PENYAKIT)
        }
        context?.let { controllerDatabase =  DatabaseController(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_penyakit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(jenisPenyakit){
            1-> {
                jenis_penyakit_label.text = getString(R.string.hipertensi)
                jenis_penyakit_subtitle.text = getString(R.string.subtitle_hipertensi)
                context?.let { list_penyakit_cardview.setCardBackgroundColor(ContextCompat.getColor(it, R.color.hipertensi)) }
            }
            2-> {
                jenis_penyakit_label.text = getString(R.string.diabetes)
                jenis_penyakit_subtitle.text = getString(R.string.subtitle_diabetes)
                context?.let { list_penyakit_cardview.setCardBackgroundColor(ContextCompat.getColor(it, R.color.diabetes)) }
            }
            3-> {
                jenis_penyakit_label.text = getString(R.string.kanker)

                jenis_penyakit_subtitle.text = getString(R.string.subtitle_kanker)
                context?.let {
                    jenis_penyakit_label.setTextColor(ContextCompat.getColor(it, android.R.color.holo_orange_dark))
                    jenis_penyakit_subtitle.setTextColor(ContextCompat.getColor(it, android.R.color.holo_orange_dark))
                    list_penyakit_cardview.setCardBackgroundColor(ContextCompat.getColor(it, R.color.kanker))
                }
            }
        }
        val penyakitList = controllerDatabase.readPenyakit(jenisPenyakit)
        penyakit_recycleview.adapter = PenyakitAdapter(context, penyakitList){
            listener?.changeFragment(SelectedPenyakitFragment.newInstance(it.id!!))
        }
        penyakit_recycleview.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance(jenisPenyakit : Int)=
                DetailPenyakitFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_JENIS_PENYAKIT, jenisPenyakit)
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
