package com.yousef.mvpflightinfo.di.module

import android.app.Application
import android.content.Context
import com.yousef.mvpflightinfo.data.AppDataManager
import com.yousef.mvpflightinfo.data.DataManager
import com.yousef.mvpflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvpflightinfo.data.network.APIInterface
import com.yousef.mvpflightinfo.data.network.ApiHelper
import com.yousef.mvpflightinfo.data.network.AppApiHelper
import com.yousef.mvpflightinfo.data.preferences.AppPreferencesHelper
import com.yousef.mvpflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvpflightinfo.di.PreferenceInfo
import com.yousef.mvpflightinfo.ui.schedules.view.SchedulesAdapter
import com.yousef.mvpflightinfo.utils.CommonUtils
import com.yousef.mvpflightinfo.utils.rx.AppSchedulerProvider
import com.yousef.mvpflightinfo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    fun providerSchedulesAdapter(context: Application): SchedulesAdapter {
        return SchedulesAdapter(context, ArrayList())
    }

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferencesHelper): PreferencesHelper = appPreferenceHelper

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return CommonUtils.PREF_NAME
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }
    @Provides
    fun getApiInterface(retroFit: Retrofit): APIInterface {
        return retroFit.create(APIInterface::class.java)
    }

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.lufthansa.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .addInterceptor { chain: Interceptor.Chain ->
                    val request: Request = chain.request().newBuilder().addHeader("Accept", "application/json")
                            .build()
                    chain.proceed(request)
                }
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @get:Provides
    val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
}