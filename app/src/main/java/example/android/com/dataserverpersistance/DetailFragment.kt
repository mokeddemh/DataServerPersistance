package example.android.com.dataserverpersistance

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.retrofit.RetrofitService
import example.android.com.dataserverpersistance.roomdatabase.RoomService
import example.android.com.dataserverpersistance.viewmodel.CityModel
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    lateinit var cityModel:CityModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val idCity = arguments?.getInt("id")
        cityModel = ViewModelProviders.of(this).get(CityModel::class.java)
        if (cityModel.city!=null) {
            displayDatail(cityModel.city!!)
        }
        else {
            loadDetail(idCity!!)
        }
    }




    fun loadDetail(idCity:Int) {
        progressBar2.visibility = View.VISIBLE
        // load city detail from SQLite DB
        cityModel.city = RoomService.appDataBase.getCityDao().getCityById(idCity)
        if(cityModel.city?.detailImage==null) {
            // if the city details don't exist, load the details from server and update SQLite DB
            loadDetailFromRemote(cityModel.city!!)
        }
        else {
            progressBar2.visibility = View.GONE
            displayDatail(cityModel.city!!)
        }

    }

    private fun loadDetailFromRemote(city:City) {
        val call = RetrofitService.endpoint.getDetailCity(city.idCity)
        call.enqueue(object : Callback<City> {
            override fun onResponse(call: Call<City>?, response: Response<City>?) {
                progressBar2.visibility = View.GONE
                if(response?.isSuccessful!!) {
                    var cityDetail = response?.body()
                    cityDetail = city!!.copy(
                            description =cityDetail?.description,
                            detailImage = cityDetail?.detailImage,
                            places = cityDetail?.places)
                    displayDatail(cityDetail)
                    // update the city in the SQLite DB to support offline mode
                    RoomService.appDataBase.getCityDao().updateCity(cityDetail)
                    // update ViewModel
                    cityModel.city = cityDetail

                }
                else {
                    Toast.makeText(activity!!,"Une erreur s'est produite", Toast.LENGTH_SHORT).show()


                }


            }

            override fun onFailure(call: Call<City>?, t: Throwable?) {
                progressBar2.visibility = View.GONE
                Toast.makeText(activity!!,"Une erreur s'est produite", Toast.LENGTH_SHORT).show()


            }
        })
    }



    fun displayDatail(city: City) {
        Glide.with(activity!!).load(baseUrl + city.detailImage).apply(
                RequestOptions().placeholder(R.drawable.place_holder))
                .into(imageDetail)

        nameDetail.text = city.name
        numbertouristDetail.text  = city.touristNumber
        description.text = city.description
        places.text = getString(R.string.places)+city.places
    }

}
