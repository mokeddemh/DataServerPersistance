package example.android.com.dataserverpersistance.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import example.android.com.dataserverpersistance.R
import example.android.com.dataserverpersistance.baseUrl
import example.android.com.dataserverpersistance.entity.City
import org.jetbrains.anko.bundleOf

class CityAdapter(val context: Context, var data:List<City>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.city_layout, parent,false)
        )

    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(baseUrl + data.get(position).listImage)
         .apply(RequestOptions().placeholder(R.drawable.place_holder))
          .into(holder.imageList)
        holder.name.setText(data.get(position).name)
        holder.numberTourist.setText(data.get(position).touristNumber)

        holder.itemView.setOnClickListener { view ->
            var bundle = bundleOf("id" to data.get(position).idCity)
            view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)

        }


    }


}


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageList = view?.findViewById<ImageView>(R.id.listimage) as ImageView
    val name = view?.findViewById<TextView>(R.id.name) as TextView
    val numberTourist = view?.findViewById<TextView>(R.id.numbertourist) as TextView


}



