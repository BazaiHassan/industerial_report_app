package com.hbazai.industreport.pages.report_page.viewModel.permit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.permit.ResponseShowPermitReportItem
import com.hbazai.industreport.pages.report_page.repository.permit.ShowPermitReportRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowPermitReportsViewModel(private val showPermitReportRepository: ShowPermitReportRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val showPermitReportLiveData = MutableLiveData<List<ResponseShowPermitReportItem>>()
    init {
        showPermitReportRepository.showPermitReports().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseShowPermitReportItem>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseShowPermitReportItem>) {
                    showPermitReportLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_SHOW_PERMIT_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}