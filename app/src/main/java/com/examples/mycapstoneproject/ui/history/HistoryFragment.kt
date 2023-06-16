package com.examples.mycapstoneproject.ui.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.Hair
import com.examples.mycapstoneproject.data.History
import com.examples.mycapstoneproject.databinding.FragmentDetailBinding
import com.examples.mycapstoneproject.databinding.FragmentHistoryBinding
import com.examples.mycapstoneproject.ui.adapter.ListHairAdapter
import com.examples.mycapstoneproject.ui.adapter.ListHistoryAdapter
import java.util.ArrayList

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }
    private var  _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<History>()
    private lateinit var rvHistory: RecyclerView

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        // TODO: Use the ViewModel
        rvHistory = binding.rvUser
        rvHistory.setHasFixedSize(true)

        list.addAll(listHistory)
        showRecyclerList()
    }
    private fun showRecyclerList() {

        rvHistory.layoutManager = LinearLayoutManager(requireActivity())

        val listHistoryAdapter = ListHistoryAdapter(list)
        rvHistory.adapter = listHistoryAdapter
    }

    private val listHistory: ArrayList<History>
        get() {
            val dataName = resources.getStringArray(R.array.data_face)
            val dataDescription = resources.getStringArray(R.array.data_date)
            val dataPhoto = resources.getStringArray(R.array.data_pho)
            val listHisto = ArrayList<History>()
            for (i in dataName.indices) {
                val his = History(dataName[i], dataDescription[i], dataPhoto[i])
                listHisto.add(his)
            }
            return listHisto
        }

}