package example.android.com.dataserverpersistance.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import example.android.com.dataserverpersistance.R
import example.android.com.dataserverpersistance.baseUrl
import example.android.com.dataserverpersistance.entity.City
import org.jetbrains.anko.toast


/**
 * Created by hakim on 3/11/18.
 */

class CityAdapter(_ctx:Context,_listCities:List<City>):BaseAdapter() {
    var ctx = _ctx
    val listCities = _listCities


    override fun getItem(p0: Int) = listCities.get(p0)

    override fun getItemId(p0: Int) = listCities.get(p0).hashCode().toLong()

    override fun getCount()= listCities.size

    override fun getView(position: Int, p0: View?, parent: ViewGroup?): View {

        var view = p0
        var viewHolder: ViewHolder
        if(view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.city_layout,parent,false)
            val imageList = view?.findViewById<ImageView>(R.id.listimage) as ImageView
            val name = view?.findViewById<TextView>(R.id.name) as TextView
            val numberTourist = view?.findViewById<TextView>(R.id.numbertourist) as TextView
            viewHolder= ViewHolder(imageList, name, numberTourist)
            view.setTag(viewHolder)
        }
        else {
            viewHolder = view.getTag() as ViewHolder

        }
            Glide.with(ctx).load(baseUrl + listCities.get(position).listImage)

                    .apply(
                    RequestOptions().placeholder(R.drawable.place_holder)
            ).into(viewHolder.imageList)

        viewHolder.name.setText(listCities.get(position).name)
        viewHolder.numberTourist.setText("${listCities.get(position).touristNumber} de touristes par an")
        return view

    }

    private data class ViewHolder(var imageList:ImageView,var name:TextView,var numberTourist:TextView)
}