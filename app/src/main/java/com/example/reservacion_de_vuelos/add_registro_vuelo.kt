package com.example.reservacion_de_vuelos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import com.example.reservacion_de_vuelos.database.AppDatabase
import com.example.reservacion_de_vuelos.database.Registro
import com.example.reservacion_de_vuelos.helper.doAsync
import kotlinx.android.synthetic.main.registro_layout.*
import kotlinx.android.synthetic.main.activity_add_registro_vuelo.*
import java.util.*

class add_registro_vuelo : AppCompatActivity() {

    companion object {
        val EXTRA_ID="extraId"
        val INSTANCE_ID="instanceId"
        private val DEFAULT_ID=-1
        private val TAG= add_registro_vuelo::class.java.simpleName

    }

    var lista_de_pagos = arrayOf("Efectivo", "Tarjeta", "Cupon")
    var origen = arrayOf("Mexico", "Estados Unidos", "China","Japon","Africa")
    var destino = arrayOf("Mexico", "Estados Unidos", "China","Japon","Africa")

    private var mTaskId= add_registro_vuelo.DEFAULT_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_registro_vuelo)



        btnagregar.setOnClickListener{
            onSaveButtonClicked()
        }

        if (savedInstanceState !=null && savedInstanceState.containsKey(add_registro_vuelo.INSTANCE_ID)){
            mTaskId=savedInstanceState.getInt(
                add_registro_vuelo.INSTANCE_ID,
                add_registro_vuelo.DEFAULT_ID
            )
        }



        //Crea un areglo para usar el simple sninner de pago
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista_de_pagos)

        // seleccionamos el modo para que aparesca en el diseño
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // mostramos en el spinner los item
        spinner!!.setAdapter(aa)
       // spinner.setAdapter[aa]

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
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?){
        outState?.putInt(add_registro_vuelo.INSTANCE_ID, mTaskId)
        super.onSaveInstanceState(outState, outPersistentState)
    }

   private fun populateUI(registro: Registro){
       if (registro==null) return
       nom_pas.setText(registro.nom_pas)
       prec_bol.setText(registro.prec_bol)
       txtvuelo.setText(registro.cod_vuel)
       num_vuel.setText(registro.num_vuel)
       num_asien.setText(registro.num_asien)
   }



    fun onSaveButtonClicked(){
        val nombre=nom_pas.text.toString()
        val precio=prec_bol.text.toString()
        val vuelo=txtvuelo.text.toString()
        val numvu=num_vuel.text.toString()
        val numas=num_asien.text.toString()


        val Entry= Registro(nom_pas=nombre, prec_bol=precio,cod_vuel=numvu,num_vuel=numas,num_asien=vuelo,updatedAt = Date())
        doAsync{
            if (mTaskId == add_registro_vuelo.DEFAULT_ID){
                AppDatabase.getInstance(this)!!.RegistroDao().insert(Entry)
            }else{
                Entry.id=mTaskId.toLong()
                AppDatabase.getInstance(this)!!.RegistroDao().update(Entry)
            }
            finish()
        }.execute()
        val intent = Intent(this, Mostrar_Tabla::class.java)
        startActivity(intent)
    }


}

