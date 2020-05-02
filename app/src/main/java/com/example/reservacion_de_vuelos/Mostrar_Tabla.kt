package com.example.reservacion_de_vuelos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reservacion_de_vuelos.database.AppDatabase
import com.example.reservacion_de_vuelos.helper.doAsync
import com.example.reservacion_de_vuelos.database.Registro
import kotlinx.android.synthetic.main.activity_mostrar__tabla.*

class Mostrar_Tabla : AppCompatActivity() {

    private lateinit var viewAdapter: AdaptadorRegistro
    private lateinit var viewManager: RecyclerView.LayoutManager

    val ListaRegistro: List<Registro> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar__tabla)

    viewManager= LinearLayoutManager(this)
  // viewAdapter= AdaptadorRegistro(ListaRegistro,this,{ registro:Registro->onItemClickListener(registro)})

        recyclerView.apply{
        setHasFixedSize(true)
        layoutManager=viewManager
        adapter=viewAdapter
        addItemDecoration(DividerItemDecoration(this@Mostrar_Tabla, DividerItemDecoration.VERTICAL))
    }

    ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }


        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

//Eliminar campos
            doAsync{
                val position = viewHolder.adapterPosition
                val registros = viewAdapter.getRegistro()
                AppDatabase.getInstance(this@Mostrar_Tabla)?.RegistroDao()?.delete(registros[position])
                retrieve()
            }.execute()


        }
    }).attachToRecyclerView(recyclerView)

}


        private fun onItemClickListener(registro:Registro){
        val intent= Intent(this,add_registro_vuelo::class.java)
        intent.putExtra(add_registro_vuelo.EXTRA_ID,registro.id)
        startActivity(intent)
    }


//Muestra el resultado de la tabla ciclo
    override fun onResume(){
        super.onResume()
        retrieve()
    }

    //Realiza una consulta a la base de datos en general y la muestra
    private fun retrieve(){
        doAsync{
            val registro= AppDatabase.getInstance(this@Mostrar_Tabla)?.RegistroDao()?.loadAll()
            runOnUiThread{
                viewAdapter.setRegistro(registro!!)
            }

        }.execute()
    }
    }

