package training.official.catalinstefanandroidcourse.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import training.official.catalinstefanandroidcourse.model.Country

class ListViewModel : ViewModel() {

    val countries = MutableLiveData<ArrayList<Country>>()
    val countriesLoadError = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    init {
       fetchCountries()
    }

    /**
     *  This method-call uses CoroutineScope tied to this ViewModel. This scope will be canceled when ViewModel will be cleared,
     *  i.e ViewModel.onCleared is called
     * This scope is bound to Dispatchers.Main.immediate
     *   androidx.lifecycle   ViewModelKt.class
     *
     *   Here 'Dispatchers' utilization is not necessary as it is filling up the list from static-data, however
     *   it is really a good practice to use, as most fetchData() like calls
     */
    private fun fetchCountries(){
        viewModelScope.launch(Dispatchers.IO) {
            val mockData = arrayListOf (
                Country("C -1 "),
                Country("C - 10 "),
                Country("C - 20 "),
                Country("C - 30"),
                Country("C - 40"),
                Country("C - 50")
            )
            withContext(Dispatchers.Main){
                countries.value = mockData
                countriesLoadError.value = false
                isLoading.value = false
            }
        }

        }

}