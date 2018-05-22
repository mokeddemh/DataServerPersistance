package example.android.com.dataserverpersistance

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import example.android.com.dataserverpersistance.viewmodel.CityModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val cityModel = ViewModelProviders.of(this).get(CityModel::class.java)
        val id = intent.getIntExtra("id",0)
        if (cityModel.city!=null) {
            cityModel.displayDatail(this,cityModel.city!!)
        }
        else {
            cityModel.loadDetail(this, id)
        }
        }




}
