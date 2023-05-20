package com.hbazai.industreport.pages.report_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.RequestDeleteReport
import com.hbazai.industreport.pages.report_page.dataModel.ResponseCreateReport
import com.hbazai.industreport.pages.report_page.repository.DeleteReportRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DeleteReportViewModel(private val deleteReportRepository: DeleteReportRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val deleteReportLiveData = MutableLiveData<ResponseCreateReport>()

    fun deleteReport(requestDeleteReport: RequestDeleteReport){
        deleteReportRepository.deleteReport(requestDeleteReport).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseCreateReport>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ResponseCreateReport) {
                    deleteReportLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_DELETE_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}