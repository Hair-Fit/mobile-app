package com.examples.mycapstoneproject.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.Hair
import com.examples.mycapstoneproject.databinding.FragmentDetailBinding
import com.examples.mycapstoneproject.ui.adapter.ListHairAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class DetailFragment : Fragment() {



    private lateinit var viewModel: DetailViewModel
    private var  _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvHair: RecyclerView
    private val list = ArrayList<Hair>()
    private lateinit var navController: NavController
    companion object {
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        navController = Navigation.findNavController(requireActivity(),
            R.id.nav_host_fragment_activity_home
        )

//        val bottomNavigationView: BottomNavigationView = root.findViewById(R.id.nav_view)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            NavigationUI.onNavDestinationSelected(item, navController)
//        }
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel

        rvHair = binding.rvUser
        rvHair.setHasFixedSize(true)

        list.addAll(listHair)
        showRecyclerList()
    }
    private fun showRecyclerList() {

            rvHair.layoutManager = LinearLayoutManager(requireActivity())

        val listHairAdapter = ListHairAdapter(list)
        rvHair.adapter = listHairAdapter
    }

    private val listHair: ArrayList<Hair>
        get() {
            val dataName = resources.getStringArray(R.array.data_hair)
            val dataDescription = resources.getStringArray(R.array.data_hairtype)
            val dataPhoto = resources.getStringArray(R.array.data_hairpho)
            val listHaire = ArrayList<Hair>()
            for (i in dataName.indices) {
                val hair = Hair(dataName[i], dataDescription[i], dataPhoto[i])
                listHaire.add(hair)
            }
            return listHaire
        }

}