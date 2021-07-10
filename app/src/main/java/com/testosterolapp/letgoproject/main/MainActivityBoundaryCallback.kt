package com.testosterolapp.letgoproject

import android.content.Context
import androidx.paging.PagedList
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.serverCommunication.GetServerData

class MainActivityBoundaryCallbackconstructor(private val context: Context) : PagedList.BoundaryCallback<Character?>() {

    override fun onZeroItemsLoaded() {
        fetchServerData(context)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Character) {
        fetchServerData(context)
    }

    private fun fetchServerData(context: Context) {
        val getServerData = GetServerData()
        getServerData.getServerData(context)
    }

}