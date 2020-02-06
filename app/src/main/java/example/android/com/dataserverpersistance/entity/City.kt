package example.android.com.dataserverpersistance.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by hakim on 3/11/18.
 */

@Entity(tableName = "cities")
data class City(
                @PrimaryKey
                @ColumnInfo(name="city_id")
                var idCity:Int,
                @ColumnInfo(name="main_url")
                var listImage:String="",
                @ColumnInfo(name="detail_url")
                var detailImage:String?="",
                var name:String="",
                @ColumnInfo(name="tourist_number")
                var touristNumber:String?="",
                var places:String?="",
                var description:String?=""):Serializable


