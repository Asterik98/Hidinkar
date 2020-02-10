package com.hwp.lingkungansehat.tips


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hwp.lingkungansehat.OnFragmentChange
import com.hwp.lingkungansehat.R
import com.hwp.lingkungansehat.db.DatabaseController
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Intent
import android.net.Uri


class HomeFragment : Fragment() {

    private var listener: OnFragmentChange? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTipsRecycleView()
    }

    private fun setTipsRecycleView(){
        context?.let {
            val listTips = DatabaseController(it).readTips()
            tips_sehat_recycleview.adapter = TipsAdapter(context, listTips) { tips ->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(tips.web_link))
                startActivity(browserIntent)
            }
        }
        tips_sehat_recycleview.layoutManager = LinearLayoutManager(context)
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
