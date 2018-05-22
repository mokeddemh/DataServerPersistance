package example.android.com.dataserverpersistance

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.viewmodel.CityModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val cityModel = ViewModelProviders.of(this).get(CityModel::class.java)
        val city = intent.getSerializableExtra("city") as City
        if (cityModel.cityModel!=null) {
            cityModel.displayDatail(this,cityModel.cityModel!!)
        }
        else {
            cityModel.loadDetail(this,city)
        }
        }




}
