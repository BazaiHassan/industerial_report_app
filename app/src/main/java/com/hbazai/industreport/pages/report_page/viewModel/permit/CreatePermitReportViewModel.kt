package com.hbazai.industreport.pages.report_page.viewModel.permit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.dataModel.permit.RequestCreatePermitReport
import com.hbazai.industreport.pages.report_page.repository.permit.CreatePermitReportRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CreatePermitReportViewModel(private val createPermitReportRepository: CreatePermitReportRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val createPermitReportLiveData = MutableLiveData<ResponseCreateReport>()

    fun createPermitReport(permitReport:RequestCreatePermitReport){
        createPermitReportRepository.createPermitReport(permitReport).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    createPermitReportLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_CREATE_PERMIT_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}