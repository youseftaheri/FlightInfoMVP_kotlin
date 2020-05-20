package com.yousef.mvpflightinfo.ui.schedules

import android.content.Context
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesAdapter
import dagger.Module
import dagger.Provides

@Module
class SchedulesAdapterModule(var context: Context, private val list: MutableList<LuftSchedulesPOJO.Schedule?>) {
    @Provides
//    @ActivityScope
    fun providerSchedulesAdapter(): SchedulesAdapter {
        return SchedulesAdapter(context, list)
    }

}