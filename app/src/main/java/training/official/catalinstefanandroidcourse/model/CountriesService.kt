package training.official.catalinstefanandroidcourse.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private val BASE_URL = "https://raw.githubusercontent.com"
    private val api : CountriesApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            /** below adapter transform 'country-data-class' into an Observable-variable similar
              * to what we have in ViewModel-class
              **/
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)       // returns this type of info that would be returned "Single (observable) list of type-Country"
    }

    fun getCountries() : Single<List<Country>> {
        return api.getCountries()      // (from api-Interface : CountriesApi)
    }
}