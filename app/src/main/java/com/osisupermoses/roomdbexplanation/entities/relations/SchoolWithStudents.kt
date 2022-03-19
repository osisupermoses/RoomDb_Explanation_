package com.osisupermoses.roomdbexplanation.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.osisupermoses.roomdbexplanation.entities.School
import com.osisupermoses.roomdbexplanation.entities.Student

data class SchoolWithStudents(
    @Embedded val school: School,
    @Relation(
        parentColumn = "schoolName",
        entityColumn = "schoolName"
    )
    val students: List<Student>
)
