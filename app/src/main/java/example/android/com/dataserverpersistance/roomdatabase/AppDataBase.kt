package example.android.com.dataserverpersistance.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.roomdao.CityDao

@Database(entities = arrayOf(City::class),version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCityDao():CityDao

}