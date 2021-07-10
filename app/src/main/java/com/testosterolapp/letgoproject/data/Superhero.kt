package com.testosterolapp.letgoproject.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Superhero {


    @PrimaryKey
    var id: Int = 0

    var modified: String? = null

    @Ignore
    var character: Character? = null

    constructor()

    constructor(id_superhero: Int, modified: String?){
        this.id = id_superhero
        this.modified = modified
    }
}