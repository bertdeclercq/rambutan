package com.xunobulax.rambutan

import android.app.Application
import timber.log.Timber


class RambutanApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}