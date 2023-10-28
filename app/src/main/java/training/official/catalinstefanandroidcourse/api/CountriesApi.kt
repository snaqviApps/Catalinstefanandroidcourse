package training.official.catalinstefanandroidcourse.api

import io.reactivex.Single
import retrofit2.http.GET
import training.official.catalinstefanandroidcourse.model.Country

interface CountriesApi {
    @GET("DevTides/countries/master/countriesV2.json")          // END_POINT = "DevTides/countries/master/countriesV2.json"
    fun getCountries() : Single<List<Country>>
}