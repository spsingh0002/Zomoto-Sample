package som.sps.zmoto.ui.main

import android.app.Application
import timber.log.Timber

class ZomatoApp : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}