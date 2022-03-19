package com.osisupermoses.roomdbexplanation.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.osisupermoses.roomdbexplanation.entities.Student
import com.osisupermoses.roomdbexplanation.entities.Subject

data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)