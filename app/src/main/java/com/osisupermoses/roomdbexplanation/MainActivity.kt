package com.osisupermoses.roomdbexplanation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.osisupermoses.roomdbexplanation.entities.Director
import com.osisupermoses.roomdbexplanation.entities.School
import com.osisupermoses.roomdbexplanation.entities.Student
import com.osisupermoses.roomdbexplanation.entities.Subject
import com.osisupermoses.roomdbexplanation.entities.relations.StudentSubjectCrossRef
import com.osisupermoses.roomdbexplanation.ui.theme.RoomDBExplanationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDBExplanationTheme {
                val dao: SchoolDao = SchoolDatabase.getInstance(this).schoolDao

                val directors = listOf(
                    Director("Mike Litoris", "Jake Wharton School"),
                    Director("Jack Goff", "Kotlin School"),
                    Director("Chris P. Chicken", "JetBrains School")
                )
                val schools = listOf(
                    School("Jake Wharton School"),
                    School("Kotlin School"),
                    School("JetBrains School")
                )
                val subjects = listOf(
                    Subject("Dating for programmers"),
                    Subject("Avoiding depression"),
                    Subject("Bug Fix Meditation"),
                    Subject("Logcat for Newbies"),
                    Subject("How to use Google")
                )
                val students = listOf(
                    Student("Beff Jezos", 2, "Kotlin School"),
                    Student("Mark Suckerberg", 5, "Jake Wharton School"),
                    Student("Gill Bates", 8, "Kotlin School"),
                    Student("Donny Jepp", 1, "Kotlin School"),
                    Student("Hom Tanks", 2, "JetBrains School")
                )
                val studentSubjectRelations = listOf(
                    StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
                    StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
                    StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
                    StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
                    StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
                    StudentSubjectCrossRef("Gill Bates", "How to use Google"),
                    StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
                    StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
                    StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
                )
                lifecycleScope.launch {
                    directors.forEach { dao.insertDirector(it) }
                    schools.forEach { dao.insertSchool(it) }
                    subjects.forEach { dao.insertSubject(it) }
                    students.forEach { dao.insertStudent(it) }
                    studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

                    val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")
                    Log.e("SCHOOLWITHDRIRECTOR", "This is a list: $schoolWithDirector")
//                    schoolWithDirector.first().director

                    val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
                    Log.e("SCHOOLWITHDSTUDENTS", "This is a list: $schoolWithStudents")
//                    schoolWithStudents.first().school
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RoomDBExplanationTheme {
        Greeting("Android")
    }
}