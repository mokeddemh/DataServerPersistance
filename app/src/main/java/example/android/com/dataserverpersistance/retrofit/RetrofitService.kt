package example.android.com.dataserverpersistance.retrofit

import example.android.com.dataserverpersistance.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hakim on 3/29/18.
 */
object RetrofitService {

     val endpoint: Endpoint by lazy {
        Retrofit.Builder().baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create()).build()
                .create(Endpoint::class.java)

     }
}