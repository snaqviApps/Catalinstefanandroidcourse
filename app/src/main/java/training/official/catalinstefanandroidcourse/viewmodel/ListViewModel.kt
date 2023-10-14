package training.official.catalinstefanandroidcourse.viewmodel

import androidx.lifecycle.ViewModel
import training.official.catalinstefanandroidcourse.model.CountriesRepository

class ListViewModel(private val repo : CountriesRepository) : ViewModel() {      // expected repository approach in next commit

    val countries = repo.countries
    val countriesLoadError = repo.countriesLoadError
    val loadingCheck = repo.loadingCheck

    override fun onCleared() {
        super.onCleared()
        repo.disposable.clear()
    }
}