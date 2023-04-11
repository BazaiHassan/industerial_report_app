package com.hbazai.industreport.pages.report_page.viewModel.chemical

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.chemical.RequestCreateChemicalReport
import com.hbazai.industreport.pages.report_page.repository.chemical.CreateChemicalReportRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreateChemicalReportViewModel(private val createChemicalReportRepository: CreateChemicalReportRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val createChemicalReportLiveData = MutableLiveData<ResponseCreateReport>()

    fun createChemicalReport(chemicalReport:RequestCreateChemicalReport){
        createChemicalReportRepository.createChemicalReport(chemicalReport).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    createChemicalReportLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CREATE_CHEMICAL_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}