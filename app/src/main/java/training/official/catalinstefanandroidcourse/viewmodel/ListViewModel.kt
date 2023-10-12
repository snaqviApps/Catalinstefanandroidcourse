package training.official.catalinstefanandroidcourse.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import training.official.catalinstefanandroidcourse.model.CountriesService
import training.official.catalinstefanandroidcourse.model.Country

class ListViewModel() : ViewModel() {      // expected repository approach in next commit

    val countriesService = CountriesService()
    val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>?>()
    val countriesLoadError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        isLoading.value = true                                                         // loading started
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())                                   // newThread is backEnd-Thread
                .observeOn(AndroidSchedulers.mainThread())                             // back to Main-Thread
                .subscribeWith(object : DisposableSingleObserver<List<Country>?>() {
                    override fun onSuccess(values: List<Country>) {
                        countries.value = values
                        isLoading.value = false
                        countriesLoadError.value = false
                    }
                    override fun onError(e: Throwable) {
                        countriesLoadError.value = true
                        isLoading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}