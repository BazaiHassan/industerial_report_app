package com.hbazai.industreport.api

import com.hbazai.industreport.pages.notify_page.dataModel.RequestCreateNotification
import com.hbazai.industreport.pages.notify_page.dataModel.ResponseShowNotificationItem
import com.hbazai.industreport.pages.report_page.dataModel.daily.RequestCreateDailyReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseUploadImage
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import com.hbazai.industreport.pages.report_page.dataModel.daily.ResponseShowReportsItem
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseShowPermitReportItem
import com.hbazai.industreport.pages.report_page.dataModel.risk.RequestCreateRiskReport
import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem
import com.hbazai.industreport.pages.search_page.dataModel.ResponseSearch
import com.hbazai.industreport.pages.user_page.auth.dataModel.RequestPhoneNumber
import com.hbazai.industreport.pages.user_page.auth.dataModel.ResponseSendOTP
import com.hbazai.industreport.utils.Constants.Companion.BASE_URL
import com.hbazai.industreport.utils.TokenContainer
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // Daily Report API
    @GET("reports/show_daily_reports.php")
    fun showDailyReports():Single<List<ResponseShowReportsItem>>

    @POST("reports/create_daily_report.php")
    fun createDailyReport(@Body dailyReport: RequestCreateDailyReport):Single<ResponseCreateReport>

    // Chemical Report API
    @GET("reports/show_chemical_reports.php")
    fun showChemicalReports():Single<List<ResponseShowChemicalReportItem>>

    @POST("reports/create_chemical_report.php")
    fun createChemicalReport(@Body chemicalReport: RequestCreateChemicalReport):Single<ResponseCreateReport>

    // Risk Report API
    @GET("reports/show_risk_reports.php")
    fun showRiskReports():Single<List<ResponseShowRiskReportItem>>

    @POST("reports/create_risk_report.php")
    fun createRiskReport(@Body riskReport: RequestCreateRiskReport):Single<ResponseCreateReport>

    // Permit Report API
    @GET("reports/show_permit_reports.php")
    fun showPermitReports():Single<List<ResponseShowPermitReportItem>>

    @POST("reports/create_permit_report.php")
    fun createPermitReport(@Body permitReport: RequestCreatePermitReport):Single<ResponseCreateReport>

    // Upload Reports image
    @Multipart
    @POST("reports/upload_report_image.php")
    fun uploadReportImage(
        @Part sendImage: MultipartBody.Part,

    ):Single<ResponseUploadImage>

    // Search Reports
    @GET("reports/search_report.php")
    fun searchReports(
        @Query("keyword") keyword: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ):Single<List<ResponseSearch>>

    // Create Notification
    @POST("notifications/create_notification.php")
    fun createNotification(
        @Body requestCreateNotification: RequestCreateNotification
    ):Single<ResponseCreateReport>


    // Show Notification
    @GET("notifications/show_notifications.php")
    fun showNotifications():Single<List<ResponseShowNotificationItem>>

    // Check Phone and Send OTP
    @POST("user/send_otp.php")
    fun checkPhoneNumber(
        @Body requestPhoneNumber:RequestPhoneNumber
    ):Single<ResponseSendOTP>

    // Get Token
    @POST("user/login_user.php")
    fun getToken(
        @Body requestPhoneNumber:RequestPhoneNumber
    ):Single<ResponseSendOTP>



}


fun retrofitApi():ApiService{

    // Define Header
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenContainer.token != null){
                newRequest.addHeader("Authorization","Bearer ${TokenContainer.token!!}")
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