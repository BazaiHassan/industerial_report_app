package com.hbazai.industreport.di

import android.app.Application
import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.api.retrofitApi
import com.hbazai.industreport.pages.report_page.adapter.ReportAdapter
import com.hbazai.industreport.pages.report_page.dataSource.daily.RemoteCreateDailyReportDataSource
import com.hbazai.industreport.pages.report_page.repository.CreateDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.ImplCreateDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.ImplShowReportRepository
import com.hbazai.industreport.pages.report_page.repository.ShowReportsRepository
import com.hbazai.industreport.pages.report_page.viewModel.CreateDailyReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.ShowReportsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            single<ApiService>{ retrofitApi() }

            factory<ShowReportsRepository> { ImplShowReportRepository(RemoteShowReportDataSource(get())) }
            viewModel { ShowReportsViewModel(get()) }
            factory { ReportAdapter() }

            factory<CreateDailyReportRepository> { ImplCreateDailyReportRepository(
                RemoteCreateDailyReportDataSource(get())
            ) }
            viewModel { CreateDailyReportViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }
}