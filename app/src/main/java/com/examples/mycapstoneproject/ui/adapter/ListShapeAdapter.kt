package com.examples.mycapstoneproject.ui.adapter

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.Muka

class ListShapeAdapter(private val listMuka: ArrayList<Muka>) : RecyclerView.Adapter<ListShapeAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_catalog,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMuka[position])
    }

    override fun getItemCount(): Int = listMuka.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private var tvName: TextView = itemView.findViewById(R.id.tv_item_name)


        fun bind(muka: Muka) {
            imgPhoto.setImageResource(muka.photo)
//            Glide.with(itemView.context)
//                .load( BitmapFactory.decodeResource(Resources.getSystem(),muka.photo))
//                .circleCrop()
//                .into(imgPhoto)
            tvName.text = muka.name


            itemView.setOnClickListener{view->
                view.findNavController().navigate(R.id.action_navigation_catalog_to_navigation_detail)
            }
//            {
//                val toCatalog = HomeFragmentDirections.actionNavigationHomeToNavigationCatalog()
//
//                it.findNavController().navigate(toCatalog)
//                val intent = Intent(itemView.context, DetailFragment::class.java)
////                val optionsCompat: ActivityOptionsCompat =
////                    ActivityOptionsCompat.makeSceneTransitionAnimation(
////                        itemView.context as Activity,
////                        Pair(imgPhoto, "profile"),
////                        Pair(tvName, "name"),
////
////                    )
////                intent.putExtra("Hero", muka)
////                itemView.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity).toBundle())
//                itemView.context.startActivity(intent )
//
//            }
        }
    }

}