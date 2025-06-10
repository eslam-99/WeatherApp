package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import com.example.weatherapp.di.repositoryModule
import com.example.weatherapp.di.uiModule
import com.example.weatherapp.ui.screens.HomeScreen
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(repositoryModule, uiModule)
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0xFFFFFF),
            navigationBarStyle = SystemBarStyle.dark(0xFFFFFF),
        )
        setContent {
            HomeScreen(get())
        }
    }
}
