package com.examples.mycapstoneproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.mycapstoneproject.R
import com.examples.mycapstoneproject.data.History

class ListHistoryAdapter(private val listHistory: ArrayList<History>) : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHistory[position])
    }

    override fun getItemCount(): Int = listHistory.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_his_photo)
        private var tvName: TextView = itemView.findViewById(R.id.tv_shape_name)
        private var tvDate: TextView = itemView.findViewById(R.id.tv_date_shot)


        fun bind(history: History) {
            Glide.with(itemView.context)
                .load(history.photo)
                .into(imgPhoto)
            tvName.text = history.name
            tvDate.text = history.description


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