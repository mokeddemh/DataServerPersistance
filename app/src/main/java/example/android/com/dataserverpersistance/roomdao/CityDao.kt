package example.android.com.dataserverpersistance.roomdao

import android.arch.persistence.room.*
import example.android.com.dataserverpersistance.entity.City

@Dao
interface CityDao {

    @Query("select * from cities")
    fun getCities():List<City>

    @Query("select * from cities where city_id=:idCity")
    fun getCityById(idCity:Int):City

    @Insert
    fun addCities(cities:List<City>)

    @Update
    fun updateCity(city: City)

}