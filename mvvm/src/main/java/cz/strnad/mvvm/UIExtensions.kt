package cz.strnad.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

fun FragmentActivity.bindViewModel(recursively: Boolean = false,
                                   factory: BaseViewModelFactory = BaseViewModelFactory(application, intent.extras ?: Bundle())) {
    MVVM.bind(this, ViewModelProviders.of(this, factory), recursively)
}

fun Fragment.bindViewModel(recursively: Boolean = false,
                           factory: BaseViewModelFactory = BaseViewModelFactory(activity!!.application, arguments ?: Bundle())) {
    MVVM.bind(this, ViewModelProviders.of(this, factory), recursively)
}
