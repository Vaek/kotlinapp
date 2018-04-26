package cz.strnad.mvvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.os.Bundle

open class BaseViewModel(application: Application, bundle: Bundle) : AndroidViewModel(application) {
}