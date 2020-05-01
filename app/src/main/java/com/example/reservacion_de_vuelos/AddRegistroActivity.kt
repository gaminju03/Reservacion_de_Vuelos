package com.example.reservacion_de_vuelos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.reservacion_de_vuelos.database.AppDatabase
import com.example.reservacion_de_vuelos.database.Registro
import com.example.reservacion_de_vuelos.helper.doAsync
import kotlinx.android.synthetic.main.activity_registro_vuelos.*
import  kotlinx.android.synthetic.main.registro_layout.*
import kotlinx.android.synthetic.main.registro_layout.view.*
import java.util.*

class AddRegistroActivity : AppCompatActivity() {
    companion object {
        val EXTRA_ID="extraId"
        val INSTANCE_ID="instanceId"
        private val DEFAULT_ID=-1
        private val TAG= AddRegistroActivity::class.java.simpleName

    }

    private var mTaskId= AddRegistroActivity.DEFAULT_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_vuelos)

        UpdatedAt.setOnClickListener{
            onSaveButtonClicked()
        }

        if (savedInstanceState !=null && savedInstanceState.containsKey(AddRegistroActivity.INSTANCE_ID)){
            mTaskId=savedInstanceState.getInt(
                AddRegistroActivity.INSTANCE_ID,
                AddRegistroActivity.DEFAULT_ID
            )
        }
        val intent=intent
        if(intent !=null && intent.hasExtra(AddRegistroActivity.EXTRA_ID)){
            UpdatedAt.text=getString(R.string.btnagregar).toString()
            if (mTaskId === AddRegistroActivity.DEFAULT_ID){
                mTaskId=intent.getLongExtra(AddRegistroActivity.EXTRA_ID, AddRegistroActivity.DEFAULT_ID.toLong()).toInt()
                doAsync{
                    val alumno= AppDatabase.getInstance(this@AddRegistroActivity)?.RegistroDao()?.loadById(mTaskId.toLong())
                    runOnUiThread{
                        populateUI(alumno!!)
                    }

                }.execute()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?){
        outState?.putInt(AddRegistroActivity.INSTANCE_ID, mTaskId)
        super.onSaveInstanceState(outState, outPersistentState)
    }
    private fun populateUI(registro: Registro){
        if (registro==null) return
        NombrePass.setText(registro.nom_pas)
        Prec_bol.setText(registro.prec_bol)
        //TxtPagoforma.setText(registro.txtPagoforma)//txt
        Spinner.setText(registro.spinner)
        //Txtvuelo.setText(registro.txtvuelo)//txt
        Num_vuel.setText(registro.num_vuel)
        Num_asien.setText(registro.num_asien)
       // Origen.setText(registro.origen)//txt
        Spinner2.setText(registro.spinner2)
       // Txtdestino.setText(registro.txtdestino)//txt
        Spndestino.setText(registro.spndestino)


    }

    fun onSaveButtonClicked(){
        val nombre=NombrePass.text.toString()
        val precio=Prec_bol.text.toString()
        val forma_pago=Spinner.text.toString()
        val num_vue=Num_vuel.text.toString()
        val num_asie=Num_asien.text.toString()
        val origen=Origen.text.toString()
        val ori=Spinner2.text.toString()
        val destino=Spndestino.text.toString()


        val Entry= Registro(nom_pas= nombre, prec_bol= precio,spinner=forma_pago,num_vuel=num_vue,num_asien=num_asie,
            origen=origen,spinner2=ori,spndestino = destino,updatedAt = Date())
        doAsync{
            if (mTaskId == AddRegistroActivity.DEFAULT_ID){
                AppDatabase.getInstance(this)!!.RegistroDao().insert(Entry)
            }else{
                Entry.id=mTaskId.toLong()
                AppDatabase.getInstance(this)!!.RegistroDao().update(Entry)
            }
            finish()
        }.execute()
    }
}