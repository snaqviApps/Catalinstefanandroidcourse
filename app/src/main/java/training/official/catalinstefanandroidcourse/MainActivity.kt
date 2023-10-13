package training.official.catalinstefanandroidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import training.official.catalinstefanandroidcourse.databinding.ActivityMainBinding
import training.official.catalinstefanandroidcourse.view.CountriesAdapter
import training.official.catalinstefanandroidcourse.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ListViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        mainBinding.lifecycleOwner = this
        initCountriesRView()

        setupObservers()

    }

    private fun initCountriesRView() {
        mainBinding.countriesListRView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        mainBinding.loadingProgressbar.visibility = View.VISIBLE
        viewModel.apply {
            countries.observe(this@MainActivity) { countries ->
                countries?.let {
                    mainBinding.countriesListRView.adapter = CountriesAdapter(it)
                    mainBinding.loadingProgressbar.visibility = View.GONE
                }
            }

            countriesLoadError.observe(this@MainActivity, Observer { loadingError ->
                if (loadingError) mainBinding.listError.visibility = View.VISIBLE else View.GONE
            })

            isLoading.observe(this@MainActivity) { isLoading ->
                isLoading?.let {
                    mainBinding.apply {
                        if (isLoading) {
                            mainBinding.apply {
                                countriesListRView.visibility = View.GONE
                                listError.visibility = View.GONE
                            }
                        } else {
                            listError.visibility = View.VISIBLE
                            countriesListRView.visibility = View.VISIBLE
                            loadingProgressbar.visibility = View.GONE
                        }
                    }
                }
            }
        }

    }

}