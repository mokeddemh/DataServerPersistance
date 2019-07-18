package example.android.com.dataserverpersistance


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import example.android.com.dataserverpersistance.adapter.CityAdapter
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.viewmodel.CityModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.intentFor


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // View Model instance
        val cityModel = ViewModelProviders.of(activity!!).get(CityModel::class.java)
        // If the list of cities is null, load the list from DB
        if (cityModel.cities==null) {
            cityModel.loadData(activity!!)
        }
        else {
            // After the rotation of the screen, use cities of the ViewModel instance
            listcities.adapter = CityAdapter(activity!!, cityModel.cities!!)
        }


        listcities.setOnItemClickListener { adapterView, view, i, l ->
            val city = (adapterView.getItemAtPosition(i) as City)
            var bundle = bundleOf("id" to city.idCity)
            view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
        }
    }
}


