package example.android.com.dataserverpersistance

import android.app.Application
import example.android.com.dataserverpersistance.roomdatabase.RoomService


class App: Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}