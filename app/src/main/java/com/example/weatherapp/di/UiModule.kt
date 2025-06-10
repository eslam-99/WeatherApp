package com.example.weatherapp.di

import com.example.weatherapp.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeScreenViewModel(get(), get()) }
}