package com.testosterolapp.letgoproject.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testosterolapp.letgoproject.data.Character
import com.testosterolapp.letgoproject.data.Superhero

@Dao
interface SuperheroDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(superhero: Superhero)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)


    @Query("SELECT name FROM character WHERE id_fk_superhero = :superheroId")
    fun getNameOfSuperhero(superheroId: Int): String

    @Query("SELECT description FROM character WHERE id_fk_superhero = :superheroId")
    fun getDescriptionOfSuperhero(superheroId: Int): String

    @Query("SELECT thumbnail FROM character WHERE id_fk_superhero = :superheroId")
    fun getThumbnailOfSuperhero(superheroId: Int): String

    @Query("SELECT * FROM character")
    open fun getInitialCharactersInUnsortedOrder(): DataSource.Factory<Int, Character>

    @Query("SELECT * FROM character ORDER BY name DESC ")
    open fun getInitialCharactersInSortedOrder(): DataSource.Factory<Int, Character>

}