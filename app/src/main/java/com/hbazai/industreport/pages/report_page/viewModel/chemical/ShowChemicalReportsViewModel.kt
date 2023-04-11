package com.hbazai.industreport.pages.report_page.viewModel.chemical

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.report_page.dataModel.chemical.ResponseShowChemicalReportItem
import com.hbazai.industreport.pages.report_page.repository.chemical.ShowChemicalReportsRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ShowChemicalReportsViewModel(private val showChemicalReportsRepository: ShowChemicalReportsRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val showChemicalReportsLiveData = MutableLiveData<List<ResponseShowChemicalReportItem>>()

    init {
        showChemicalReportsRepository.showChemicalReports().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseShowChemicalReportItem>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseShowChemicalReportItem>) {
                    showChemicalReportsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_SHOW_CHEMICAL_REPORT", "onError:${e.message} ")
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}