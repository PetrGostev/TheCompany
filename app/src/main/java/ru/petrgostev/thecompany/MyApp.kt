package ru.petrgostev.thecompany

import android.app.Application
import android.content.Context
import com.yandex.mapkit.MapKitFactory

class MyApp : Application() {
    companion object {
        private const val MAP_KEY = "bde7ef65-ff37-48da-84db-2bbc872a932c"
    }

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey(MAP_KEY)
        MapKitFactory.initialize(this)
    }
}