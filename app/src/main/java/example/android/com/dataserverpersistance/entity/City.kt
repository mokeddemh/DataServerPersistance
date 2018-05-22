package example.android.com.dataserverpersistance.entity

import java.io.Serializable

/**
 * Created by hakim on 3/11/18.
 */

// The entity with default constructor

data class City(
                var idCity:Int,
                var listImage:String="",
                var detailImage:String="",
                var name:String="",
                var touristNumber:String="",
                var places:String="",
                var description:String=""):Serializable {
}
