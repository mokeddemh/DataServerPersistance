package example.android.com.dataserverpersistance


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import example.android.com.dataserverpersistance.adapter.CityAdapter
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.retrofit.RetrofitService
import example.android.com.dataserverpersistance.roomdatabase.RoomService
import example.android.com.dataserverpersistance.viewmodel.CityModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    lateinit var cityModel:CityModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // View Model instance
        cityModel = ViewModelProviders.of(activity!!).get(CityModel::class.java)
        // If the list of cities is null, load the list from DB
        if (cityModel.cities==null) {
            loadData()
        }
        else {
            // After the rotation of the screen, use cities of the ViewModel instance
          displayList()
        }

    }




    fun loadData() {
        progressBar1.visibility = View.VISIBLE
        // Get cities from SQLite DB
        cityModel.cities = RoomService.appDataBase.getCityDao().getCities()

        if (cityModel.cities?.size == 0) {
            // If the list of cities is empty, load from server and save them in SQLite DB
            getCitiesFromRemote()
        }
        else {
            progressBar1.visibility = View.GONE
            displayList()
        }




    }


    private fun getCitiesFromRemote() {
        val call = RetrofitService.endpoint.getCities()
        call.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>?, response: Response<List<City>>?) {
                progressBar1.visibility = View.GONE
                if (response?.isSuccessful!!) {
                    cityModel.cities = response?.body()
                    progressBar1.visibility = View.GONE
                    displayList()
                    // save cities in SQLite DB
                    RoomService.appDataBase.getCityDao().addCities(cityModel.cities!!)
                } else {
                    Toast.makeText(activity!!,"Une erreur s'est produite",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<City>>?, t: Throwable?) {
                progressBar1.visibility = View.GONE
                Toast.makeText(activity!!,"Une erreur s'est produite",Toast.LENGTH_SHORT).show()
            }


        })
    }



    private fun displayList() {
        listcities.layoutManager = LinearLayoutManager(activity!!)
        listcities.adapter = CityAdapter(activity!!,cityModel.cities!!)
        listcities.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
    }



}


