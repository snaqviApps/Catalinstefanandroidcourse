package training.official.catalinstefanandroidcourse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import training.official.catalinstefanandroidcourse.model.CountriesRepository

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val repository : CountriesRepository) :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return super.create(modelClass)
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            return ListViewModel(repo = repository) as T
        }
        throw IllegalArgumentException("")
    }
}