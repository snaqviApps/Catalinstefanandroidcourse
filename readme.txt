
branch: add_backend_call
it adds
 - api to make backEnd call
 - service that uses the api for getting Countries-List as Json using Gson
 - RxJava that makes the Country of type 'data-class' into an Observable (similar to LiveData-types we use in ViewModel
   however with RxJava core-characteristics

 branch: add_dagger2
  initial dagger using 'ksp' and not 'kapt'. 'CountriesApi' is being injected by dagger2 (creating and injecting) instead of manually
  It helps in separating the creation and using a 'variable', here: CountriesApi

  branch: add_dagger2
      in class: CountriesRepository, line# 12, countriesService initialization was replaced with dagger-injection approach as it
      old-approach had dependencies upon Retrofit, Rxjava, not good for SRP, and Unit testing


