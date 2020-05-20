package com.yousef.mvpflightinfo.di.component

import android.app.Application
import com.yousef.mvpflightinfo.MyApplication
import com.yousef.mvpflightinfo.di.builder.ActivityBuilder
import com.yousef.mvpflightinfo.di.module.AppModule
//import com.yousef.mvpflightinfo.ui.schedules.SchedulesAdapterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])//, SchedulesAdapterModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)

}