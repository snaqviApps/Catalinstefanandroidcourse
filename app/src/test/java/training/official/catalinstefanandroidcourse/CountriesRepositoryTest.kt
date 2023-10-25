package training.official.catalinstefanandroidcourse

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import training.official.catalinstefanandroidcourse.model.CountriesRepository
import training.official.catalinstefanandroidcourse.model.CountriesService
import training.official.catalinstefanandroidcourse.model.Country
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class CountriesRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countriesService: CountriesService


    @InjectMocks
    var repository = CountriesRepository()

    private var testSingle: Single<List<Country>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getCountriesSuccess() {
        val country = Country("countryName", "capital", "url")
        val countriesList = arrayListOf(country)

        testSingle = Single.just(countriesList)

        repository.countriesLoadError.value = true
        repository.fetchCountries()

        `when`(countriesService.getCountries()).thenReturn(testSingle)

        Assert.assertEquals(1, repository.countries.value?.size)
//        Assert.assertEquals(false, listViewModel.countryLoadError.value)
//        Assert.assertEquals(false, listViewModel.loading.value)
    }

//    @Test
//    fun getCountriesFail() {
//        testSingle = Single.error(Throwable())
//
//        `when`(countriesService.getCountries()).thenReturn(testSingle)
//
//        listViewModel.refresh()
//
//        Assert.assertEquals(true, listViewModel.countryLoadError.value)
//        Assert.assertEquals(false, listViewModel.loading.value)
//    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
               return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler> -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}