package com.hbazai.industreport.pages.report_page.viewModel.risk

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.risk.ResponseShowRiskReportItem
import com.hbazai.industreport.pages.report_page.repository.risk.ShowRiskReportsRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowRiskReportsViewModel(private val showRiskReportsRepository: ShowRiskReportsRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val showRiskReportsLiveData = MutableLiveData<List<ResponseShowRiskReportItem>>()

    init {
        showRiskReportsRepository.showRiskReports().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseShowRiskReportItem>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseShowRiskReportItem>) {
                    showRiskReportsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_SHOW_RISK_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}