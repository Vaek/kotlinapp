package cz.strnad.kotlinapp.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.strnad.kotlinapp.R
import cz.strnad.kotlinapp.mvvm.MainViewModel
import cz.strnad.mvvm.BindViewModel
import cz.strnad.mvvm.bindViewModel
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    @BindViewModel
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViewModel()

        viewModel.orders.data.observe(this, Observer { t -> t?.toString()?.let { toast(it) } })
        viewModel.orders.error.observe(this, Observer { t -> t?.getTranslation()?.let { toast(it) } })
        viewModel.loadOrder()
    }
}
