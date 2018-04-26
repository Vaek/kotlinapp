package cz.strnad.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.bindViewModel(recursively: Boolean = false,
                                   factory: BaseViewModelFactory = BaseViewModelFactory(application, intent.extras)) {
    MVVM.bind(this, ViewModelProviders.of(this, factory), recursively)
}

fun Fragment.bindViewModel(recursively: Boolean = false,
                           factory: BaseViewModelFactory = BaseViewModelFactory(activity!!.application, arguments!!)) {
    MVVM.bind(this, ViewModelProviders.of(this, factory), recursively)
}
