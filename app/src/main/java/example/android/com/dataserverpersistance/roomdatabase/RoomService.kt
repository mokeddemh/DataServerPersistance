package example.android.com.dataserverpersistance.roomdatabase

import android.arch.persistence.room.Room
import android.content.Context

object RoomService {


    lateinit var context: Context

    val appDataBase: AppDataBase by lazy {
        Room.databaseBuilder(context,AppDataBase::class.java,"dbcities").allowMainThreadQueries().build()
    }
}