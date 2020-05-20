package com.yousef.mvpflightinfo.di.builder

import com.yousef.mvpflightinfo.ui.main.MainActivityModule
import com.yousef.mvpflightinfo.ui.main.view.MainActivity
import com.yousef.mvpflightinfo.ui.map.MapActivityModule
import com.yousef.mvpflightinfo.ui.map.view.MapActivity
import com.yousef.mvpflightinfo.ui.schedules.SchedulesActivityModule
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesActivity
import com.yousef.mvpflightinfo.ui.splash.SplashActivityModule
import com.yousef.mvpflightinfo.ui.splash.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SchedulesActivityModule::class])
    abstract fun bindSchedulesActivity(): SchedulesActivity

    @ContributesAndroidInjector(modules = [(MapActivityModule::class)])
    abstract fun bindMapActivity(): MapActivity
}