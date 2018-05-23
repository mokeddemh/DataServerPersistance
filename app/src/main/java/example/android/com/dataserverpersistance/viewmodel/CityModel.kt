package example.android.com.dataserverpersistance.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.adapter.CityAdapter
import example.android.com.dataserverpersistance.R
import example.android.com.dataserverpersistance.retrofit.RetrofitService
import example.android.com.dataserverpersistance.baseUrl
import example.android.com.dataserverpersistance.roomdatabase.RoomService
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by hakim on 3/11/18.
 */


class CityModel:ViewModel() {


    var cityModel: City? = null
    var cities:List<City>? = null


    fun loadData(act:Activity) {
          act.progressBar1.visibility = View.VISIBLE
            // Get cities from SQLite DB
            cities = RoomService.appDataBase.getCityDao().getCities()

            if (cities?.size == 0) {
            // If the list of cities is empty, load from server and save them in SQLite DB
                getCitiesFromRemote(act)
            }
            else {
                act.progressBar1.visibility = View.GONE
                act.listcities.adapter = CityAdapter(act, cities!!)
            }




    }

    private fun getCitiesFromRemote(act:Activity) {
        val call = RetrofitService.endpoint.getCities()
        call.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>?, response: Response<List<City>>?) {
                act.progressBar1.visibility = View.GONE
                if (response?.isSuccessful!!) {
                    cities = response?.body()
                    act.progressBar1.visibility = View.GONE
                    act.listcities.adapter = CityAdapter(act, cities!!)
                    // save cities in SQLite DB
                    RoomService.appDataBase.getCityDao().addCities(cities!!)
                } else {
                    act.toast("Une erreur s'est produite")
                }
            }

            override fun onFailure(call: Call<List<City>>?, t: Throwable?) {
                act.progressBar1.visibility = View.GONE
                act.toast("Une erreur s'est produite")
            }


        })
    }

    fun loadDetail(act:Activity,city:City) {
        act.progressBar2.visibility = View.VISIBLE
        // load city detail from SQLite DB
        cityModel = RoomService.appDataBase.getCityDao().getCityById(city.idCity)
        if(cityModel?.detailImage==null) {
            // if the city details don't exist, load details from server and update SQLite DB
           loadDetailFromRemote(act,city)
        }
        else {
            act.progressBar2.visibility = View.GONE
            displayDatail(act, cityModel!!)
        }

    }

    private fun loadDetailFromRemote(act:Activity,city:City) {
        val call = RetrofitService.endpoint.getDetailCity(city.idCity)
        call.enqueue(object : Callback<City> {
            override fun onResponse(call: Call<City>?, response: Response<City>?) {
                act.progressBar2.visibility = View.GONE
                if(response?.isSuccessful!!) {
                    // update city model null values
                    cityModel = response?.body()
                    cityModel?.idCity = city.idCity
                    cityModel?.name = city.name
                    cityModel?.touristNumber = city.touristNumber
                    cityModel?.listImage = city.listImage
                    displayDatail(act, cityModel!!)
                    // update the city in the SQLite DB to support offline mode
                    RoomService.appDataBase.getCityDao().updateCity(cityModel!!)

                }
                else {
                    act.toast("Une erreur s'est produite")

                }


            }

            override fun onFailure(call: Call<City>?, t: Throwable?) {
                act.progressBar2.visibility = View.GONE
                act.toast("Une erreur s'est produite")

            }
        })
    }

    fun displayDatail(act: Activity,city: City) {
        val call = RetrofitService.endpoint.getDetailCity(city.idCity)
        Glide.with(act).load(baseUrl + city.detailImage).apply(
                RequestOptions().placeholder(R.drawable.place_holder)
        ).into(act.imageDetail)

        act.nameDetail.text = city.name
        act.numbertouristDetail.text  = city.touristNumber
        act.description.text = city.description
        act.places.text = act.getString(R.string.places)+city.places
    }


}