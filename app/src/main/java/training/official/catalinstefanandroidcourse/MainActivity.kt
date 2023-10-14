package training.official.catalinstefanandroidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import training.official.catalinstefanandroidcourse.databinding.ActivityMainBinding
import training.official.catalinstefanandroidcourse.model.CountriesRepository
import training.official.catalinstefanandroidcourse.view.CountriesAdapter
import training.official.catalinstefanandroidcourse.viewmodel.ListViewModel
import training.official.catalinstefanandroidcourse.viewmodel.ListViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var listRepo: CountriesRepository
    private lateinit var viewModel: ListViewModel
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        listRepo = CountriesRepository()
        val viwModelFactory = ListViewModelFactory(repository = listRepo)
        viewModel = ViewModelProvider(this, viwModelFactory)[ListViewModel::class.java]
        mainBinding.lifecycleOwner = this
        refresh()
        initCountriesRView()
        setupObservers()
    }

    private fun initCountriesRView() {
        mainBinding.countriesListRView.layoutManager = LinearLayoutManager(this)
    }
    private fun refresh() {
        mainBinding.apply {
            swipeLayoutId.setOnRefreshListener {
                swipeLayoutId.isRefreshing = false
                listRepo.fetchCountries()
            }
        }
    }

    private fun setupObservers() {
        viewModel.apply {
            countries.observe(this@MainActivity) { countries ->
                countries?.let {
                    mainBinding.countriesListRView.adapter = CountriesAdapter(it)
                    mainBinding.loadingProgressbar.visibility = View.GONE
                }
            }

            countriesLoadError.observe(this@MainActivity, Observer { isError ->
                isError?.let { mainBinding.tvLoadingError.visibility = if(it) View.VISIBLE else View.GONE }
            })

            loadingCheck.observe(this@MainActivity) { isLoading ->
                isLoading?.let {
                    mainBinding.apply {
                        if (isLoading) {
                                loadingProgressbar.visibility = View.VISIBLE
                                tvLoadingError.visibility = View.GONE
                        } else {
                            loadingProgressbar.visibility = View.GONE
                            tvLoadingError.visibility = View.VISIBLE
                            countriesListRView.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

}