package example.android.com.dataserverpersistance.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import example.android.com.dataserverpersistance.entity.City
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


    var city: City? = null
    var cities:List<City>? = null





}