package training.official.catalinstefanandroidcourse.di

import dagger.Component
import training.official.catalinstefanandroidcourse.model.CountriesRepository
import training.official.catalinstefanandroidcourse.api.CountriesService

@Component(modules = [ApiModule::class])
interface ApiComponent {

    /**
     *  the method 'inject' is injecting required info/component from ApiModule to 'CountriesService' class
     */
    fun inject(service: CountriesService)

    fun inject(countryRepository: CountriesRepository)
}