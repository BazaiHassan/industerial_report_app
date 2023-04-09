package com.hbazai.industreport.api

import androidx.core.view.accessibility.AccessibilityEventCompat.ContentChangeType
import com.hbazai.industreport.pages.report_page.dataModel.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReports
import com.hbazai.industreport.pages.report_page.dataModel.ResponseShowReportsItem
import com.hbazai.industreport.utils.Constants.Companion.BASE_URL
import com.hbazai.industreport.utils.TokenContainer
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Here we must send request based on date and type
    @GET("reports/show_daily_reports.php")
    fun showDailyReports():Single<List<ResponseShowReportsItem>>

    @POST("reports/create_daily_report.php")
    fun createDailyReport(@Body dailyReport: RequestCreateDailyReport):Single<ResponseCreateReport>
}


fun retrofitApi():ApiService{

    // Define Header
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenContainer.token != null){
                newRequest.addHeader("Authorization",TokenContainer.token!!)
            }
            newRequest.method(oldRequest.method, oldRequest.body)
            return@addInterceptor it.proceed(newRequest.build())

        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}