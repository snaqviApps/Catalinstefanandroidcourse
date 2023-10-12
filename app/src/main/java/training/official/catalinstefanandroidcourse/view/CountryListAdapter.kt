package training.official.catalinstefanandroidcourse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training.official.catalinstefanandroidcourse.databinding.CountryListItemBinding
import training.official.catalinstefanandroidcourse.model.Country

class CountriesAdapter(private var countries : List<Country>)
    : RecyclerView.Adapter<CountriesAdapter.CountriesListViewHolder>() {

    /** not being used */
//    fun updateCountries(newCountries : List<Country>) {
//        countries.clear()
//        countries.addAll(newCountries)
//        notifyDataSetChanged()              // not recommended
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemBinding = CountryListItemBinding.inflate(layoutInflater)
        return CountriesListViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountriesListViewHolder, position: Int) =
        holder.bind(countries[position])


    class CountriesListViewHolder(private val countryItem: CountryListItemBinding) : RecyclerView.ViewHolder(countryItem.root) {
        fun bind(country: Country){
            countryItem.itemName.text = country.countryName
        }
    }

}