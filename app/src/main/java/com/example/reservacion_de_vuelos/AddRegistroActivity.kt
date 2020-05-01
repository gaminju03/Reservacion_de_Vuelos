package com.example.reservacion_de_vuelos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.reservacion_de_vuelos.database.AppDatabase
import com.example.reservacion_de_vuelos.database.Registro
import com.example.reservacion_de_vuelos.helper.doAsync
import kotlinx.android.synthetic.main.activity_registro_vuelos.*
import java.util.*

class AddRegistroActivity {
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

        saveButton.setOnClickListener{
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
            saveButton.text=getString(R.string.update_button).toString()
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
        editTextNombre.setText(registro.nom_pas)
        tvApellido.setText(registro.prec_bol)
        tvEspe.setText(registro.txtPagoforma)
        tvDom.setText(registro.spinner)
        tvTel.setText(registro.txtvuelo)
        tvTel.setText(registro.num_vuel)
        tvTel.setText(registro.num_asien)
        tvTel.setText(registro.origen)
        tvTel.setText(registro.spinner2)
        tvTel.setText(registro.txtdestino)
        tvTel.setText(registro.spndestino)
        tvTel.setText(registro.btnagregar)

    }

    fun onSaveButtonClicked(){
        val nombre=editTextNombre.text.toString()
        val precio=tvApellido.text.toString()
        val especialidad=tvEspe.text.toString()
        val domicilio=tvDom.text.toString()
        val telefono=tvTel.text.toString()


        val Entry= Registro(nom_pas= nombre, prec_bol= precio,txtPagoforma=domicilio,telefono=telefono,especialidad=especialidad,updatedAt = Date())
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