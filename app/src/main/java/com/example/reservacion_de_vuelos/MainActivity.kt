package com.example.reservacion_de_vuelos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro_vuelos.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            if (editText3.text.toString().equals("juan")
                    && editText4.text.toString().equals("password")) {

                    val intent = Intent(this, add_registro_vuelo::class.java)

                    startActivity(intent)

                }


               else {


               Toast.makeText(this, "ogin fallo", Toast.LENGTH_SHORT).show()

           }
        }
    }




    }



