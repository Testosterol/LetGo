package com.testosterolapp.letgoproject.database

import android.content.Context
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.data.Superhero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DaoRepository {

    var superheroDao: SuperheroDao? = null

    constructor(context: Context) : this() {
        val database = Database.getDatabase(context)
        this.superheroDao = database.superheroDao()!!
    }

    constructor()

    suspend fun insertSuperhero(superhero: Superhero) {
        return superheroDao?.insert(superhero)!!
    }

    suspend fun insertCharacterOfSuperhero(character: Character) {
        return superheroDao?.insert(character)!!
    }


    fun getNameOfSuperhero(superheroId: Int?, onComplete: OnGetNameOfSuperhero?) {
        CoroutineScope(Dispatchers.Main).launch {
            onComplete?.onComplete(superheroDao?.getNameOfSuperhero(superheroId!!))
        }
    }

    interface OnGetNameOfSuperhero {
        suspend fun onComplete(name: String?)
    }


    fun getDescriptionOfSuperhero(superheroId: Int?, onComplete: OnGetDescriptionOfSuperhero?) {
        CoroutineScope(Dispatchers.Main).launch {
            onComplete?.onComplete(superheroDao?.getDescriptionOfSuperhero(superheroId!!))
        }
    }

    interface OnGetDescriptionOfSuperhero {
        suspend fun onComplete(descriptionOfSuperhero: String?)
    }



    fun getThumbnailOfSuperhero(superheroId: Int?, onComplete: OnGetThumbnailOfSuperhero?) {
        CoroutineScope(Dispatchers.Main).launch {
            onComplete?.onComplete(superheroDao?.getThumbnailOfSuperhero(superheroId!!))
        }
    }

    interface OnGetThumbnailOfSuperhero {
        suspend fun onComplete(thumbnail: String?)
    }



}