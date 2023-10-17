package training.official.catalinstefanandroidcourse.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import training.official.catalinstefanandroidcourse.model.CountriesApi
import training.official.catalinstefanandroidcourse.model.CountriesService

/**
 * exposes the Api (for Countries-GET call) by 'creating' it
 */

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun providesCountriesApi() : CountriesApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    fun providesCountriesService() : CountriesService {
        return CountriesService()
    }
}