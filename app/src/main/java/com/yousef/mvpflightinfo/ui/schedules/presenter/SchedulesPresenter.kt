package com.yousef.mvpflightinfo.ui.schedules.presenter

import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.ui.base.presenter.BasePresenter
import com.yousef.mvpflightinfo.ui.schedules.interactor.SchedulesMVPInteractor
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesMVPView
import com.yousef.mvpflightinfo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SchedulesPresenter <V : SchedulesMVPView, I : SchedulesMVPInteractor>
@Inject
internal constructor(interactor: I, schedulerProvider: SchedulerProvider,
                     disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor,
        schedulerProvider = schedulerProvider, compositeDisposable = disposable), SchedulesMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }

    override fun loadData() {
        getView()?.showProgress()
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        interactor?.let {
            it.requestLuftSchedules("Bearer " + interactor?.getAccessToken(), date)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<LuftSchedulesPOJO?> {
                        override fun onCompleted() {
                            getView()?.hideProgress()
                        }

                        override fun onError(e: Throwable) {
                            //  mView.showError("Error occurred");
                            getView()?.hideProgress()
                        }

                        override fun onNext(data: LuftSchedulesPOJO?) {
                            getView()?.showData(data!!)
                        }
                    })
        }
    }

    override fun getOrigin(): String? {
        return interactor?.getOrigin()
    }

    override fun getDestination(): String?{
        return interactor?.getDestination()
    }

}