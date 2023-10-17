package training.official.catalinstefanandroidcourse.di

import dagger.Component
import training.official.catalinstefanandroidcourse.model.CountriesService

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
}