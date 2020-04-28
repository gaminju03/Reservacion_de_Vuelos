package com.example.reservacion_de_vuelos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Creamos los item para usar en el spiner de pago, origen y destino
    var lista_de_pagos = arrayOf("Efectivo", "Tarjeta", "Cupon")
    var origen = arrayOf("Mexico", "Estados Unidos", "China","Japon","Africa")
    var destino = arrayOf("Mexico", "Estados Unidos", "China","Japon","Africa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
           var estatus =  if (editText3.text.toString().equals("hola")
               && editText4.text.toString().equals("password")) "Login es correcto"
               else "login fallo"
            
            Toast.makeText(this, estatus,Toast.LENGTH_SHORT).show()
        }


        //Crea un areglo para usar el simple sninner de pago
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista_de_pagos)

        // seleccionamos el modo para que aparesca en el diseño
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
      //  spinner!!.setAdapter(aa)



        //Crea un areglo para usar el simple sninner de Origen
        val oo = ArrayAdapter(this, android.R.layout.simple_spinner_item, origen)

        // seleccionamos el modo para que aparesca en el diseño
        oo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
      //  spinner2!!.setAdapter(oo)


        //Crea un areglo para usar el simple sninner de Destino
        val dd = ArrayAdapter(this, android.R.layout.simple_spinner_item, destino)

        // seleccionamos el modo para que aparesca en el diseño
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
     //   spndestino!!.setAdapter(dd)


    }



}

