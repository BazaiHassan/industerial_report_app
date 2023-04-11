package com.hbazai.industreport.di

import android.app.Application
import com.hbazai.industreport.api.ApiService
import com.hbazai.industreport.api.retrofitApi
import com.hbazai.industreport.pages.report_page.adapter.ChemicalReportAdapter
import com.hbazai.industreport.pages.report_page.adapter.ReportAdapter
import com.hbazai.industreport.pages.report_page.dataSource.chemical.RemoteCreateChemicalReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.chemical.RemoteShowChemicalReportsDataSource
import com.hbazai.industreport.pages.report_page.dataSource.daily.RemoteCreateDailyReportDataSource
import com.hbazai.industreport.pages.report_page.dataSource.daily.RemoteShowDailyReportDataSource
import com.hbazai.industreport.pages.report_page.repository.chemical.CreateChemicalReportRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.ImplCreateChemicalReportRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.ImplShowChemicalReportsRepository
import com.hbazai.industreport.pages.report_page.repository.chemical.ShowChemicalReportsRepository
import com.hbazai.industreport.pages.report_page.repository.daily.CreateDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.daily.ImplCreateDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.daily.ImplShowDailyReportRepository
import com.hbazai.industreport.pages.report_page.repository.daily.ShowDailyReportsRepository
import com.hbazai.industreport.pages.report_page.viewModel.chemical.CreateChemicalReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.chemical.ShowChemicalReportsViewModel
import com.hbazai.industreport.pages.report_page.viewModel.daily.CreateDailyReportViewModel
import com.hbazai.industreport.pages.report_page.viewModel.daily.ShowDailyReportsViewModel
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
            factory { ReportAdapter() }

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
        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }
}