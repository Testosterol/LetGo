package com.testosterolapp.letgoproject.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = [ForeignKey(entity = Superhero::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("id_fk_superhero"),
    onDelete = ForeignKey.CASCADE)])
class Character {

    @PrimaryKey(autoGenerate = true)
    var id_character: Int = 0

    var id_fk_superhero: Int? = 0
    var name: String? = null
    var description: String? = null
    var thumbnail: String? = null

    constructor(id_fk_superhero: Int?, name: String?, description: String?, thumbnail: String?) {
        this.id_fk_superhero = id_fk_superhero
        this.name = name
        this.description = description
        this.thumbnail = thumbnail
    }


}