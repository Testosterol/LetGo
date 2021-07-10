package com.testosterolapp.letgoproject.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.data.Superhero
import com.testosterolapp.letgoproject.database.DaoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class DbUtil {

    companion object{

        fun launchCoroutineForInsertDataIntoDatabase(dataJsonObject: JSONObject, context: Context){
            CoroutineScope(Dispatchers.IO).launch {
                insertData(dataJsonObject, context)
            }
        }

        private suspend fun insertData(dataJsonObject: JSONObject, context: Context) {
            val daoRepository = DaoRepository(context)
            val jsonArr = dataJsonObject.getJSONArray("results")
            for (j in 0 until jsonArr.length()) {
                val superheroItemGSONConverted = Gson().fromJson(jsonArr.getJSONObject(j).toString(), Superhero::class.java)
                val superheroToInsert = Superhero(
                    superheroItemGSONConverted.id,
                    superheroItemGSONConverted.modified
                )
                daoRepository.insertSuperhero(superheroToInsert)
                insertCharacterOfSuperhero(superheroItemGSONConverted.id, jsonArr.getJSONObject(j).getJSONObject("character"), daoRepository)
            }
        }

        private suspend fun insertCharacterOfSuperhero(
            idSuperhero: Int,
            jsonObject: JSONObject?,
            daoRepository: DaoRepository
        ) {
            val gson = Gson()
            val gsonConverter = gson.fromJson(jsonObject.toString(), Character::class.java)
            val characterToBeInserted = Character(
                idSuperhero,
                gsonConverter.name,
                gsonConverter.description,
                gsonConverter.thumbnail
            )
            daoRepository.insertCharacterOfSuperhero(characterToBeInserted)
        }
    }
}