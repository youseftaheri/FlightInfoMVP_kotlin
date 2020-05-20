package com.yousef.mvpflightinfo.ui.splash.presenter

import com.yousef.mvpflightinfo.data.model.TokenPOJO
import com.yousef.mvpflightinfo.ui.base.presenter.BasePresenter
import com.yousef.mvpflightinfo.ui.splash.interactor.SplashMVPInteractor
import com.yousef.mvpflightinfo.ui.splash.view.SplashMVPView
import com.yousef.mvpflightinfo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class SplashPresenter <V : SplashMVPView, I : SplashMVPInteractor>
@Inject
internal constructor(interactor: I, schedulerProvider: SchedulerProvider,
                     disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor,
        schedulerProvider = schedulerProvider, compositeDisposable = disposable), SplashMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        requestAccessTokenPost()
    }

    fun requestAccessTokenPost() {
        getView()?.showProgress()
        interactor?.let {
            it.requestAccessTokenPost()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<TokenPOJO?> {
                        override fun onCompleted() {
                            getView()?.hideProgress()
                        }

                        override fun onError(e: Throwable) {
                            //  mView.showError("Error occurred");
                            getView()?.hideProgress()
                        }

                        override fun onNext(data: TokenPOJO?) {
                            interactor?.setAccessToken(data!!.access_token!!)
                            getView()?.showData(data!!)
                        }
                    })
        }
    }
}