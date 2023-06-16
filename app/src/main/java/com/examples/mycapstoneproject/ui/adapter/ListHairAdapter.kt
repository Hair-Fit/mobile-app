package com.examples.mycapstoneproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.Hair

class ListHairAdapter(private val listHair: ArrayList<Hair>) : RecyclerView.Adapter<ListHairAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_detail,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHair[position])
    }

    override fun getItemCount(): Int = listHair.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_det_photo)
        private var tvName: TextView = itemView.findViewById(R.id.tv_hair_style)
        private var tvType: TextView = itemView.findViewById(R.id.tv_type_name)


        fun bind(hair: Hair) {
            Glide.with(itemView.context)
                .load(hair.photo)
                .circleCrop()
                .into(imgPhoto)
            tvName.text = hair.name
            tvType.text = hair.description


//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, DetailFragment::class.java)
//                val optionsCompat: ActivityOptionsCompat =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        itemView.context as Activity,
//                        Pair(imgPhoto, "profile"),
//                        Pair(tvName, "name"),
//
//                        )
//                intent.putExtra("Hero", muka)
////                itemView.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity).toBundle())
//                itemView.context.startActivity(intent, optionsCompat.toBundle())
//
//            }
        }
    }

}