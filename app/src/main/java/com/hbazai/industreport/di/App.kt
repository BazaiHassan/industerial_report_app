package com.hbazai.industreport.di

import android.app.Application
import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.api.retrofitApi
import com.hbazai.industreport.pages.notify_page.adapter.NotificationAdapter
import com.hbazai.industreport.pages.notify_page.viewModel.CreateNotificationViewModel
import com.hbazai.industreport.pages.notify_page.dataSource.RemoteCreateNotificationDataSource
import com.hbazai.industreport.pages.notify_page.dataSource.RemoteShowNotificationDataSource
import com.hbazai.industreport.pages.notify_page.repository.CreateNotificationRepository
import com.hbazai.industreport.pages.notify_page.repository.ImplCreateNotificationRepository
import com.hbazai.industreport.pages.notify_page.repository.ImplShowNotificationRepository
import com.hbazai.industreport.pages.notify_page.repository.ShowNotificationRepository
import com.hbazai.industreport.pages.notify_page.viewModel.ShowNotificationViewModel
import com.hbazai.industreport.pages.report_page.adapter.*
import com.hbazai.industreport.pages.report_page.dataSource.RemoteUploadReportImageDataSource
import com.hbazai.industreport.pages.report_page.dataSource.chemical.RemoteCreateChemicalReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.chemical.RemoteShowChemicalReportsDataSource
import com.hbazai.industreport.pages.report_page.dataSource.comment.RemoteCommentDataSource
import com.hbazai.industreport.pages.report_page.dataSource.daily.RemoteCreateDailyReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.daily.RemoteShowDailyReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.permit.RemoteCreatePermitReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.permit.RemoteShowPermitReportsDataSource
import com.hbazai.industreport.pages.report_page.dataSource.risk.RemoteCreateRiskReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.risk.RemoteShowRiskReportsDataSource
import com.hbazai.industreport.pages.report_page.repository.ImplUploadReportImageRepository
import com.hbazai.industreport.pages.report_page.repository.UploadReportImageRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.CreateChemicalReportRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.ImplCreateChemicalReportRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.ImplShowChemicalReportsRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.ShowChemicalReportsRepository
import com.hbazai.industreport.pages.report_page.repository.comment.CommentRepository
import com.hbazai.industreport.pages.report_page.repository.comment.ImplCommentRepository
import com.hbazai.industreport.pages.report_page.repository.daily.CreateDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.daily.ImplCreateDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.daily.ImplShowDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.daily.ShowDailyReportsRepository
import com.hbazai.industreport.pages.report_page.repository.permit.CreatePermitReportRepository
import com.hbazai.industreport.pages.report_page.repository.permit.ImplCreatePermitReportRepository
import com.hbazai.industreport.pages.report_page.repository.permit.ImplShowPermitReportsRepository
import com.hbazai.industreport.pages.report_page.repository.permit.ShowPermitReportRepository
import com.hbazai.industreport.pages.report_page.repository.risk.CreateRiskReportRepository
import com.hbazai.industreport.pages.report_page.repository.risk.ImplCreateRiskReportRepository
import com.hbazai.industreport.pages.report_page.repository.risk.ImplShowRiskReportsRepository
import com.hbazai.industreport.pages.report_page.repository.risk.ShowRiskReportsRepository
import com.hbazai.industreport.pages.report_page.viewModel.UploadReportImageViewModel
import com.hbazai.industreport.pages.report_page.viewModel.chemical.CreateChemicalReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.chemical.ShowChemicalReportsViewModel
import com.hbazai.industreport.pages.report_page.viewModel.comment.CommentViewModel
import com.hbazai.industreport.pages.report_page.viewModel.daily.CreateDailyReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.daily.ShowDailyReportsViewModel
import com.hbazai.industreport.pages.report_page.viewModel.permit.CreatePermitReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.permit.ShowPermitReportsViewModel
import com.hbazai.industreport.pages.report_page.viewModel.risk.CreateRiskReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.risk.ShowRiskReportsViewModel
import com.hbazai.industreport.pages.search_page.adapter.SearchReportAdapter
import com.hbazai.industreport.pages.search_page.dataSource.RemoteSearchReportsDataSource
import com.hbazai.industreport.pages.search_page.repository.ImplSearchReportsRepository
import com.hbazai.industreport.pages.search_page.repository.SearchReportsRepository
import com.hbazai.industreport.pages.search_page.viewModel.SearchReportsViewModel
import com.hbazai.industreport.pages.user_page.auth.dataSource.RemoteAuthDataSource
import com.hbazai.industreport.pages.user_page.auth.dataSource.RemoteShowUserInfoDataSource
import com.hbazai.industreport.pages.user_page.auth.repository.AuthRepository
import com.hbazai.industreport.pages.user_page.auth.repository.ImplAuthRepository
import com.hbazai.industreport.pages.user_page.auth.repository.ImplShowUserInfoRepository
import com.hbazai.industreport.pages.user_page.auth.repository.ShowUserInfoRepository
import com.hbazai.industreport.pages.user_page.auth.viewModel.CheckPhoneViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.GetTokenViewModel
import com.hbazai.industreport.pages.user_page.auth.viewModel.ShowUserInfoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single<ApiService>{ retrofitApi() }

            // Daily Report Instances
            factory<ShowDailyReportsRepository> { ImplShowDailyReportRepository(RemoteShowDailyReportDataSource(get())) }
            viewModel { ShowDailyReportsViewModel(get()) }
            factory { DailyReportAdapter() }

            factory<CreateDailyReportRepository> { ImplCreateDailyReportRepository(
                RemoteCreateDailyReportDataSource(get())
            ) }
            viewModel { CreateDailyReportViewModel(get()) }

            // Chemical Report Instances
            factory<CreateChemicalReportRepository> { ImplCreateChemicalReportRepository(RemoteCreateChemicalReportDataSource(get())) }
            viewModel { CreateChemicalReportViewModel(get()) }

            factory<ShowChemicalReportsRepository> { ImplShowChemicalReportsRepository(RemoteShowChemicalReportsDataSource(get())) }
            viewModel { ShowChemicalReportsViewModel(get()) }
            factory { ChemicalReportAdapter() }

            // Risk Report Instances
            factory<CreateRiskReportRepository> { ImplCreateRiskReportRepository(RemoteCreateRiskReportDataSource(get())) }
            viewModel { CreateRiskReportViewModel(get()) }

            factory<ShowRiskReportsRepository> { ImplShowRiskReportsRepository(RemoteShowRiskReportsDataSource(get())) }
            viewModel { ShowRiskReportsViewModel(get()) }
            factory { RiskReportAdapter() }

            // Permit Report Instances
            factory<CreatePermitReportRepository> { ImplCreatePermitReportRepository(RemoteCreatePermitReportDataSource(get())) }
            viewModel { CreatePermitReportViewModel(get()) }

            factory<ShowPermitReportRepository> { ImplShowPermitReportsRepository(RemoteShowPermitReportsDataSource(get())) }
            viewModel { ShowPermitReportsViewModel(get()) }
            factory { PermitReportAdapter() }

            // Permit Image Uploading
            factory<UploadReportImageRepository> { ImplUploadReportImageRepository(RemoteUploadReportImageDataSource(get())) }
            viewModel {UploadReportImageViewModel(get())}

            // Search Reports
            factory<SearchReportsRepository> { ImplSearchReportsRepository(RemoteSearchReportsDataSource(get())) }
            viewModel { SearchReportsViewModel(get()) }
            factory { SearchReportAdapter() }

            // Create Notification
            factory<CreateNotificationRepository> { ImplCreateNotificationRepository(RemoteCreateNotificationDataSource(get())) }
            viewModel { CreateNotificationViewModel(get()) }

            // Show Notifications
            factory<ShowNotificationRepository> { ImplShowNotificationRepository(RemoteShowNotificationDataSource(get())) }
            viewModel { ShowNotificationViewModel(get()) }
            factory { NotificationAdapter() }

            // Auth User
            factory<AuthRepository> { ImplAuthRepository(RemoteAuthDataSource(get())) }
            viewModel { CheckPhoneViewModel(get()) }
            viewModel { GetTokenViewModel(get()) }

            // Show User Info
            factory<ShowUserInfoRepository> { ImplShowUserInfoRepository(RemoteShowUserInfoDataSource(get())) }
            viewModel { ShowUserInfoViewModel(get()) }

            // Comments
            factory<CommentRepository> { ImplCommentRepository(RemoteCommentDataSource(get())) }
            viewModel { CommentViewModel(get()) }
            factory { AdapterComments() }

        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }
}