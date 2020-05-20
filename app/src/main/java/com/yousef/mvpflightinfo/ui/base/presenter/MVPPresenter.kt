package com.yousef.mvpflightinfo.ui.base.presenter

import com.yousef.mvpflightinfo.ui.base.interactor.MVPInteractor
import com.yousef.mvpflightinfo.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?
}