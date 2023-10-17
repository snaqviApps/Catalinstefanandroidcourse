package training.official.catalinstefanandroidcourse.model

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import training.official.catalinstefanandroidcourse.di.DaggerApiComponent
import javax.inject.Inject

class CountriesRepository {

    @Inject
    lateinit var countriesService : CountriesService
    //    private val countriesService = CountriesService()       // Old creation-approach has dependencies upon Retrofit, Rxjava, not good for SRP, and Unit testing

    val disposable = CompositeDisposable()
    val countries = MutableLiveData<List<Country>?>()
    val countriesLoadError = MutableLiveData<Boolean>()
    val loadingCheck = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
        fetchCountries()
    }

    fun fetchCountries() {
        loadingCheck.value = true                                                      // loading started
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread())                                   // newThread is backEnd-Thread
                .observeOn(AndroidSchedulers.mainThread())                             // back to Main-Thread
                .subscribeWith(object : DisposableSingleObserver<List<Country>?>() {
                    override fun onSuccess(values: List<Country>) {
                        countries.value = values
                        loadingCheck.value = false
                        countriesLoadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        countriesLoadError.value = true
                        loadingCheck.value = false
                    }
                })
        )
    }

}
