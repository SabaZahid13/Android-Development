package com.example.pdfcreator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var convertTextButton: Button
    private lateinit var convertImageButton: Button
    private lateinit var viewPdfButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertTextButton = findViewById(R.id.convertTextButton)
        convertImageButton = findViewById(R.id.convertImageButton)
        viewPdfButton = findViewById(R.id.viewPdfButton)

        convertTextButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ConvertTextToPdf::class.java)
            startActivity(intent)

        }
        convertImageButton.setOnClickListener {
            Log.d("message","Inside main convertImageButton")
            val intent = Intent(this@MainActivity, ConvertImageToPdf::class.java)
            startActivity(intent)
        }
        viewPdfButton.setOnClickListener{
            val intent = Intent(this@MainActivity, ViewPdf::class.java)
            startActivity(intent)
            Log.d("message","Inside main viewPdfButton")
        }
    }
}
