package example.android.com.dataserverpersistance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.viewmodel.CityModel


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val idCity = arguments?.getInt("id")
        val cityModel = ViewModelProviders.of(this).get(CityModel::class.java)
        if (cityModel.city!=null) {
            cityModel.displayDatail(activity!!,cityModel.city!!)
        }
        else {
            cityModel.loadDetail(activity!!,idCity!!)
        }
    }
}
