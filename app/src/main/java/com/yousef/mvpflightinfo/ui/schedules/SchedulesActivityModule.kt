package com.yousef.mvpflightinfo.ui.schedules

import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.ui.schedules.interactor.SchedulesInteractor
import com.yousef.mvpflightinfo.ui.schedules.interactor.SchedulesMVPInteractor
import com.yousef.mvpflightinfo.ui.schedules.presenter.SchedulesMVPPresenter
import com.yousef.mvpflightinfo.ui.schedules.presenter.SchedulesPresenter
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesAdapter
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesMVPView
import dagger.Module
import dagger.Provides
import android.content.Context as Context1

@Module
class SchedulesActivityModule {

    @Provides
    internal fun provideSchedulesInteractor(schedulesInteractor: SchedulesInteractor): SchedulesMVPInteractor = schedulesInteractor

    @Provides
    internal fun provideSchedulesPresenter(schedulesPresenter: SchedulesPresenter<SchedulesMVPView, SchedulesMVPInteractor>)
            : SchedulesMVPPresenter<SchedulesMVPView, SchedulesMVPInteractor> = schedulesPresenter
//    @Provides
//    internal fun provideSchedulesAdapter(context: android.content.Context,list: MutableList<LuftSchedulesPOJO.Schedule?>): SchedulesAdapter {
//        return SchedulesAdapter(context, ArrayList())
//    }

//    @Provides
//    fun provideStudentFinListAdapter(): StudentFinListAdapter {
//        return StudentFinListAdapter(java.util.ArrayList())
//    }
}