package com.yousef.mvpflightinfo.ui.map.presenter

import com.yousef.mvpflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvpflightinfo.ui.base.presenter.BasePresenter
import com.yousef.mvpflightinfo.ui.map.interactor.MapMVPInteractor
import com.yousef.mvpflightinfo.ui.map.view.MapMVPView
import com.yousef.mvpflightinfo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MapPresenter <V : MapMVPView, I : MapMVPInteractor>
@Inject
internal constructor(interactor: I, schedulerProvider: SchedulerProvider,
                     disposable: CompositeDisposable) : BasePresenter<V, I>(interactor = interactor,
        schedulerProvider = schedulerProvider, compositeDisposable = disposable), MapMVPPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }

    override fun loadData() {
        getView()?.showProgress()
        interactor?.let {
            it.requestLatLong("Bearer " + interactor?.getAccessToken(), interactor?.getOrigin())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<AirportLatLongPOJO?> {
                        override fun onCompleted() {
                            getView()?.hideProgress()
                        }

                        override fun onError(e: Throwable) {
                            //  mView.showError("Error occurred");
                            getView()?.hideProgress()
                        }

                        override fun onNext(data: AirportLatLongPOJO?) {
                            interactor?.setOrgLat(data!!.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Latitude!!)
                            interactor?.setOrgLng(data!!.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Longitude!!)
                            interactor?.let {
                                it.requestLatLong("Bearer " + interactor?.getAccessToken(), interactor?.getDestination())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(object : Observer<AirportLatLongPOJO?> {
                                            override fun onCompleted() {
                                                getView()?.hideProgress()
                                            }

                                            override fun onError(e: Throwable) {
                                                //  mView.showError("Error occurred");
                                                getView()?.hideProgress()
                                            }

                                            override fun onNext(data: AirportLatLongPOJO?) {
                                                interactor?.setDstLat(data!!.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Latitude!!)
                                                interactor?.setDstLng(data!!.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Longitude!!)
                                                getView()?.showData()
                                            }
                                        })
                            }
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

    override fun getOrgLat(): String? {
        return interactor?.getOrgLat()
    }

    override fun getOrgLng(): String?{
        return interactor?.getOrgLng()
    }

    override fun getDstLat(): String? {
        return interactor?.getDstLat()
    }

    override fun getDstLng(): String?{
        return interactor?.getDstLng()
    }
}