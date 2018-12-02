package com.garr.pavelbobrovko.omertextest.app

import android.app.Application
import com.garr.pavelbobrovko.omertextest.inject.AppComponent
import com.garr.pavelbobrovko.omertextest.inject.AppModule
import com.garr.pavelbobrovko.omertextest.inject.DaggerAppComponent

class TestApp: Application() {

    companion object {
        lateinit var instance: TestApp
        @JvmStatic lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}