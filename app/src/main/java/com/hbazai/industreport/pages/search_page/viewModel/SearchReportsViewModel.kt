package com.hbazai.industreport.pages.search_page.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hbazai.industreport.pages.search_page.dataModel.ResponseSearch
import com.hbazai.industreport.pages.search_page.repository.SearchReportsRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchReportsViewModel(private val searchReportsRepository: SearchReportsRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val searchReportsLiveDta = MutableLiveData<List<ResponseSearch>>()
    fun searchReports(
        keyword: String,
        startDate: String,
        endDate: String
    ){
        searchReportsRepository.searchReports(keyword, startDate, endDate).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ResponseSearch>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<ResponseSearch>) {
                    searchReportsLiveDta.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG_ERROR_SEARCH_REPORT", "onError:${e.message} ")

                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}