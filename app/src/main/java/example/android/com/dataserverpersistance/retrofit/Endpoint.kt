package example.android.com.dataserverpersistance.retrofit


import example.android.com.dataserverpersistance.entity.City
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by hakim on 3/29/18.
 */
interface Endpoint {

    @GET("getlistcities")
    fun getCities():Call<List<City>>

    @GET("getcitydetail/{id}")
    fun getDetailCity(@Path("id") id:Int):Call<City>


}