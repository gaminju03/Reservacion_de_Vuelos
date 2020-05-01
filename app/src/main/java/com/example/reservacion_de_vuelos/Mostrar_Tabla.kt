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
//Mostar Resicle view

    private lateinit var viewAdapter: AdaptadorRegistro
    private lateinit var viewManager: RecyclerView.LayoutManager

    val ListaAlumnos: List<Registro> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar__tabla)

    //
    viewManager= LinearLayoutManager(this)
    viewAdapter= AdaptadorRegistro(ListaAlumnos,this,{ registro:Registro->onItemClickListener(registro)})

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

    fab.setOnClickListener { view ->

        val addIntent = Intent(this@Mostrar_Tabla, AddRegistroActivity::class.java)
        startActivity(addIntent)
    }
}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun onItemClickListener(registro:Registro){
        val intent= Intent(this,AddRegistroActivity::class.java)
        intent.putExtra(AddRegistroActivity.EXTRA_ID,registro.id)
        startActivity(intent)

    }

    override fun onResume(){
        super.onResume()
        retrieve()
    }

    private fun retrieve(){
        doAsync{
            val registro= AppDatabase.getInstance(this@Mostrar_Tabla)?.RegistroDao()?.loadAll()
            runOnUiThread{
                viewAdapter.setRegistro(registro!!)
            }

        }.execute()
    }
    }

