package cz.strnad.mvvm

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Created by Vašek Strnad on 13.02.2018.
 */

object MVVM {

    fun bind(target: Any, viewModelProvider: ViewModelProvider, recursively: Boolean) {
        var clazz: Class<*>? = target.javaClass
        do {

            val declaredFields = clazz!!.declaredFields
            for (i in declaredFields.indices) {
                val annotation = declaredFields[i].getAnnotation(BindViewModel::class.java)
                if (annotation != null) {
                    try {
                        var clazzVM = annotation.value.java
                        if (clazzVM == BaseViewModel::class.java) {
                            clazzVM = declaredFields[i].type as Class<out BaseViewModel>
                        }
                        declaredFields[i].set(target, viewModelProvider.get(clazzVM))
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    } catch (e: ClassCastException) {
                        e.printStackTrace()
                    }

                }
            }

            clazz = clazz.superclass

        } while (recursively && clazz != null && FragmentActivity::class.java != clazz && Fragment::class.java != clazz)
    }

}
