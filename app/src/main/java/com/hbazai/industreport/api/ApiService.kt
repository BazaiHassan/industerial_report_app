package com.hbazai.industreport.api

import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseCreateChemicalReportItem
import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseCreatePermitReportItem
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseCreateRiskReportItem
import com.hbazai.industreport.utils.Constants.Companion.BASE_URL
import com.hbazai.industreport.utils.TokenContainer
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Daily Report API
    @GET("reports/show_daily_reports.php")
    fun showDailyReports():Single<List<ResponseShowReportsItem>>

    @POST("reports/create_daily_report.php")
    fun createDailyReport(@Body dailyReport: RequestCreateDailyReport):Single<ResponseCreateReport>

    // Chemical Report API
    @GET("reports/show_chemical_reports.php")
    fun showChemicalReports():Single<List<ResponseCreateChemicalReportItem>>

    @POST("reports/create_chemical_report.php")
    fun createChemicalReport(@Body chemicalReport: RequestCreateChemicalReport):Single<ResponseCreateReport>

    // Risk Report API
    @GET("reports/show_risk_reports.php")
    fun showRiskReports():Single<List<ResponseCreateRiskReportItem>>

    @POST("reports/create_risk_report.php")
    fun createRiskReport(@Body riskReport: RequestCreateRiskReport):Single<ResponseCreateReport>

    // Permit Report API
    @GET("reports/show_permit_reports.php")
    fun showPermitReports():Single<List<ResponseCreatePermitReportItem>>

    @POST("reports/create_permit_report.php")
    fun createPermitReport(@Body permitReport: RequestCreatePermitReport):Single<ResponseCreateReport>



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