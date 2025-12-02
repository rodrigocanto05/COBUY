package pt.iade.ei.cobuy

import android.app.Application
import pt.iade.ei.cobuy.storage.utils.TokenManager

class App : Application() {

    lateinit var tokenManager: TokenManager
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        tokenManager = TokenManager(this)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
