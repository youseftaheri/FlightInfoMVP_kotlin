package com.yousef.mvpflightinfo.ui.main.presenter

import com.yousef.mvpflightinfo.data.model.AirportsPOJO
import com.yousef.mvpflightinfo.ui.base.presenter.BasePresenter
import com.yousef.mvpflightinfo.ui.main.interactor.MainMVPInteractor
import com.yousef.mvpflightinfo.ui.main.view.MainMVPView
import com.yousef.mvpflightinfo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter <V : MainMVPView, I : MainMVPInteractor>
@Inject
internal constructor(interactor: I, schedulerProvider: SchedulerProvider,
                     disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor,
        schedulerProvider = schedulerProvider, compositeDisposable = disposable), MainMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        loadData("Bearer " + interactor?.getAccessToken())
    }

    fun loadData(token: String?) {
        getView()?.showProgress()
        interactor?.let {
            it.requestAirports(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<AirportsPOJO?> {
                        override fun onCompleted() {
                            getView()?.hideProgress()
                        }

                        override fun onError(e: Throwable) {
                            //  mView.showError("Error occurred");
                            getView()?.hideProgress()
                        }

                        override fun onNext(data: AirportsPOJO?) {
                            getView()?.showData(data!!)
                        }
                    })
        }
    }

    override fun setOrigin(origin: String) {
        interactor?.setOrigin(origin)
    }

    override fun setDestination(destination: String) {
        interactor?.setDestination((destination))
    }
}