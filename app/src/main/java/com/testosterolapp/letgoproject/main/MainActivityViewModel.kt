package com.testosterolapp.letgoproject.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.testosterolapp.letgoproject.MainActivityBoundaryCallbackconstructor
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.database.SuperheroDao

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var allRepositories: LiveData<PagedList<Character>>? = null
    val filterTextAll = MutableLiveData<String>()
    private val repositoryBoundaryCallback = MainActivityBoundaryCallbackconstructor(application)

    fun init(superheroDao: SuperheroDao) {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(8)
            .setPrefetchDistance(10)
            .build()

        allRepositories = Transformations.switchMap(filterTextAll) { input: String? ->
            if (input == null || input == "" || input == "%%") {
                return@switchMap LivePagedListBuilder(
                    superheroDao.getInitialCharactersInUnsortedOrder(),
                    pagedListConfig
                )
                    .setBoundaryCallback(repositoryBoundaryCallback)
                    .build()
            } else {
                return@switchMap LivePagedListBuilder(
                    superheroDao.getInitialCharactersInSortedOrder(),
                    pagedListConfig
                )
                    .setBoundaryCallback(repositoryBoundaryCallback)
                    .build()
            }
        }
    }
}