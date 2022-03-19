package com.osisupermoses.roomdbexplanation.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.osisupermoses.roomdbexplanation.entities.Director
import com.osisupermoses.roomdbexplanation.entities.School

data class SchoolAndDirector(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val director: Director
)