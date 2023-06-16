package com.examples.mycapstoneproject.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.Hair
import com.examples.mycapstoneproject.databinding.ActivityDetailBinding
import com.examples.mycapstoneproject.ui.adapter.ListHairAdapter
import java.util.ArrayList


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var rvHair: RecyclerView
    private val list = ArrayList<Hair>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvHair = binding.rvUser
        rvHair.setHasFixedSize(true)

        list.addAll(listHair)
        showRecyclerList()
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

    }
    private fun showRecyclerList() {

        rvHair.layoutManager = LinearLayoutManager(this)

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