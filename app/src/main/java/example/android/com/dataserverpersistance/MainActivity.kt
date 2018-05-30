package example.android.com.dataserverpersistance


import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import example.android.com.dataserverpersistance.adapter.CityAdapter
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.viewmodel.CityModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.intentFor


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // View Model instance
        val cityModel = ViewModelProviders.of(this).get(CityModel::class.java)
        // If the listof cities is null, load them from DB
        if (cityModel.cities==null) {
            // load data
            cityModel.loadData(this)
        }
        else {
            // After screen rotation, use cities of the ViewMODEL
            listcities.adapter = CityAdapter(this, cityModel.cities!!)
        }

        // Display detail data if width >= 600 dp

        if (isTwoPane() && cityModel.city!=null) {
            cityModel.displayDatail(this,cityModel.city!!)

        }

        // ListView OnClick Item

        listcities.setOnItemClickListener { adapterView, view, i, l ->
            val city = (adapterView.getItemAtPosition(i) as City)
            if (isTwoPane()) {
                // display detail data
               cityModel.loadDetail(this,city)
            }
            else {
                startActivity(intentFor<DetailActivity>("city" to city))
            }
            }
    }

    fun isTwoPane() = findViewById<View>(R.id.fragment2) != null



}

