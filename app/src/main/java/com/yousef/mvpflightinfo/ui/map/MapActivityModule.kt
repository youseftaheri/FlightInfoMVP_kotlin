package com.yousef.mvpflightinfo.ui.map

import com.yousef.mvpflightinfo.ui.map.interactor.MapInteractor
import com.yousef.mvpflightinfo.ui.map.interactor.MapMVPInteractor
import com.yousef.mvpflightinfo.ui.map.presenter.MapMVPPresenter
import com.yousef.mvpflightinfo.ui.map.presenter.MapPresenter
import com.yousef.mvpflightinfo.ui.map.view.MapMVPView
import dagger.Module
import dagger.Provides

@Module
class MapActivityModule {

    @Provides
    internal fun provideMapInteractor(mapInteractor: MapInteractor): MapMVPInteractor = mapInteractor

    @Provides
    internal fun provideMapPresenter(mapPresenter: MapPresenter<MapMVPView, MapMVPInteractor>)
            : MapMVPPresenter<MapMVPView, MapMVPInteractor> = mapPresenter
}