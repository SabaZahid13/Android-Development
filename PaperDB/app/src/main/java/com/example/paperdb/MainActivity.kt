package com.example.paperdb

import io.paperdb.Paper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnSaveRecord: Button
    private lateinit var btnGetRecord: Button
    private lateinit var studentList:Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtName = findViewById<EditText>(R.id.edtName)
        edtEmail = findViewById<EditText>(R.id.edtEmail)
        btnSaveRecord = findViewById<Button>(R.id.btnSave)
        btnGetRecord = findViewById<Button>(R.id.btnGetRecord)
        btnSaveRecord.setOnClickListener(View.OnClickListener {

            if (!edtName.text.toString().isEmpty() && !edtEmail.text.toString().isEmpty()) {
                Paper.book().write(
                    "student",
                    Student(
                        edtName.text.toString(),
                        edtEmail.text.toString())) }
            else {
                val student: Student
                student = Student(
                    "saba",
                    "sabazahid@gmail.com")
                Paper.book().write("student", student) } })

        btnGetRecord.setOnClickListener(View.OnClickListener {
            Log.d("message", "button pressed")
            // Retrieve the list of students
            val retrievedStudent = Paper.book().read("student") as? Student
            if (retrievedStudent != null) {
                // Process the single student object
                Log.d("Student", "Name: ${retrievedStudent.name}, Email: ${retrievedStudent.email}")
            } else {
                // Handle the case where no data is available
                Log.d("Student", "No student data available")
            }
        })
    } //delete data for one key
    //Paper.book().delete("contacts");
    //delete all key from the given book
    //Paper.book.destroy
    //get all keys
    //List<String> allKeys = Paper.book().getAllKeys();
}