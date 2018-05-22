package example.android.com.dataserverpersistance.roomdatabase

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import example.android.com.dataserverpersistance.entity.City
import example.android.com.dataserverpersistance.roomdao.CityDao

@Database(entities = arrayOf(City::class),version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getCityDao():CityDao

}