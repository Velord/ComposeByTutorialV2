package com.example.composebytutorialv2.app

import android.app.Application
import com.example.composebytutorialv2.di.section2.DependencyInjector

class JetNotesApplication : Application() {

    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}