package cz.strnad.mvvm

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle

/**
 * Created by Vašek Strnad on 30.01.2018.
 *
 * Creates a `BaseViewModelFactory`
 *
 * @param application an application to pass in [BaseViewModel]
 * @param bundle      an bundle to pass in [BaseViewModel]
 */

class BaseViewModelFactory(private val application: Application, private val bundle: Bundle) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(Application::class.java, Bundle::class.java).newInstance(application, bundle)
            } catch (e: Exception) {
                throw RuntimeException("Cannot create an instance of " + modelClass, e)
            }
        }
        return super.create(modelClass)
    }
}
