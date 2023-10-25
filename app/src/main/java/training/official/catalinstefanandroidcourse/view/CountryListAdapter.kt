package training.official.catalinstefanandroidcourse.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import training.official.catalinstefanandroidcourse.databinding.CountryListItemBinding
import training.official.catalinstefanandroidcourse.model.Country
import training.official.catalinstefanandroidcourse.util.getProgressDrawable
import training.official.catalinstefanandroidcourse.util.loadImage

class CountriesAdapter(private var countries : List<Country>)
    : RecyclerView.Adapter<CountriesAdapter.CountriesListViewHolder>() {

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

        private val progressDrawable = getProgressDrawable(countryItem.root.context)
        fun bind(country: Country){
            countryItem.itemCountry.text = country.countryName
            countryItem.itemCapital.text = country.capital
            countryItem.itemImage.loadImage(country.flagUrl, progressDrawable)            // created loadImage as an 'extension-function' to ImageView)
        }
    }

}


