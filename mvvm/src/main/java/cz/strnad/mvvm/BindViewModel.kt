package cz.strnad.mvvm

import kotlin.reflect.KClass

/**
 * Created by Vašek Strnad on 30.01.2018.
 */

@Target(AnnotationTarget.FIELD)
annotation class BindViewModel(val value: KClass<out BaseViewModel> = BaseViewModel::class)
