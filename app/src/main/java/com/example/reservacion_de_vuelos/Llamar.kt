package com.example.reservacion_de_vuelos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_llamar.*

class Llamar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llamar)


        val intent = Intent(Intent.ACTION_DIAL)
        startActivity(intent)

        llamada.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val num = Uri.parse("tel:"+ 4321002556)
                val i = Intent(Intent.ACTION_CALL, num)
                startActivity(i)
            }
        })




    }




}
