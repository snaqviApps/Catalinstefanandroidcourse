package training.official.catalinstefanandroidcourse.api

import io.reactivex.Single
import training.official.catalinstefanandroidcourse.di.DaggerApiComponent
import training.official.catalinstefanandroidcourse.model.Country
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api : CountriesApi

    init {
        /**
         * DaggerApiComponent class
         * created by Dagger: (name of interface; preceded by Dagger)
         * note here: we do not need to create the "api" now (so lateinit, line# 10)
         * and 'Dagger' will create and inject it
         *  this is separation b/w creating a variable and using it
         */
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries() : Single<List<Country>> {
        return api.getCountries()
    }
}