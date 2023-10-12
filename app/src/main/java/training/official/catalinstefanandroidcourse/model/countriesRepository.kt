package training.official.catalinstefanandroidcourse.model

import androidx.lifecycle.MutableLiveData

class countriesRepository {

    // next commit implementation
    private val countriesService = CountriesService()
    val countries = MutableLiveData<ArrayList<Country>>()
    val countriesLoadError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()


}
