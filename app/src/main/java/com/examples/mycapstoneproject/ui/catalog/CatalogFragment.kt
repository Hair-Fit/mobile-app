package com.examples.mycapstoneproject.ui.catalog


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.Muka
import com.examples.mycapstoneproject.databinding.FragmentCatalogBinding
import com.examples.mycapstoneproject.databinding.FragmentDetailBinding
import com.examples.mycapstoneproject.databinding.FragmentProfileBinding
import com.examples.mycapstoneproject.ui.adapter.ListShapeAdapter
import com.examples.mycapstoneproject.ui.notifications.NotificationsViewModel
import java.util.ArrayList
import java.util.Arrays

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null


    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Muka>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)

        val root: View = binding.root



//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val notificationsViewModel =
            ViewModelProvider(this).get(CatalogViewModel::class.java)

        rvHeroes = binding.rvUser
        rvHeroes.setHasFixedSize(true)
        list.addAll(listMukas)

        showRecyclerList()

//        list.addAll(listHeroes)
//        val adapter = ListShapeAdapter(list)
//        binding.rvUser.adapter = adapter
//        val layoutManager = LinearLayoutManager(requireActivity())
//        binding.rvUser.layoutManager = layoutManager




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showRecyclerList() {


        rvHeroes.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = ListShapeAdapter(list)
        rvHeroes.adapter = listHeroAdapter
    }

    private val listMukas: ArrayList<Muka>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
           val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

            val listMuka = ArrayList<Muka>()
            for (i in dataName.indices) {
                val muka = Muka(dataName[i], dataDescription[i], dataPhoto.getResourceId(i,-1))
                listMuka.add(muka)
            }
            return listMuka
        }

}