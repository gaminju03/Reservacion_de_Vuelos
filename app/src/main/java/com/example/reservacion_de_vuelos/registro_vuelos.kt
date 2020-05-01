package com.example.reservacion_de_vuelos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.reservacion_de_vuelos.database.AppDatabase
import com.example.reservacion_de_vuelos.database.Registro
import com.example.reservacion_de_vuelos.helper.doAsync
import kotlinx.android.synthetic.main.activity_registro_vuelos.*

class registro_vuelos : AppCompatActivity() {

    //Creamos los item para usar en el spiner de pago, origen y destino
    var lista_de_pagos = arrayOf("Efectivo", "Tarjeta", "Cupon")
    var origen = arrayOf("Mexico", "Estados Unidos", "China","Japon","Africa")
    var destino = arrayOf("Mexico", "Estados Unidos", "China","Japon","Africa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vuelos)


        //Crea un areglo para usar el simple sninner de pago
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista_de_pagos)

        // seleccionamos el modo para que aparesca en el diseño
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
        spinner!!.setAdapter(aa)

///omited selected funcion


        //Crea un areglo para usar el simple sninner de Origen
        val oo = ArrayAdapter(this, android.R.layout.simple_spinner_item, origen)

        // seleccionamos el modo para que aparesca en el diseño
        oo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
        spinner2!!.setAdapter(oo)


        //Crea un areglo para usar el simple sninner de Destino
        val dd = ArrayAdapter(this, android.R.layout.simple_spinner_item, destino)

        // seleccionamos el modo para que aparesca en el diseño
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
        spndestino!!.setAdapter(dd)
    }

    }
