package com.example.reservacion_de_vuelos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reservacion_de_vuelos.database.Registro
import kotlinx.android.synthetic.main.registro_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


class AdaptadorRegistro(private var mTaskEntries:List<Registro>, private val mContext: Context, private val clickListener: (Registro) -> Unit) : RecyclerView.Adapter<AdaptadorRegistro.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorRegistro.ViewHolder {

        val layoutInflater = LayoutInflater.from(mContext)
        return AdaptadorRegistro.ViewHolder(layoutInflater.inflate(R.layout.registro_layout, parent, false)
        )

    }

    override fun getItemCount(): Int = mTaskEntries.size

    override fun onBindViewHolder(holder: AdaptadorRegistro.ViewHolder, position: Int) {
        holder.bind(mTaskEntries[position], mContext, clickListener)
    }

    fun setRegistro(taskEntries: List<Registro>){
        mTaskEntries = taskEntries
        notifyDataSetChanged()
    }

    fun getRegistro():List<Registro> = mTaskEntries

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        ///alumno = nombre de la tabla  y Alumno = Nombre de la clase
        fun bind (registro: Registro, context: Context, clickListener: (Registro) -> Unit){

            itemView.NombrePass.text = registro.nom_pas
            itemView.Prec_bol.text = registro.prec_bol
            itemView.TxtPagoforma.text = registro.txtPagoforma
            itemView.Spinner.text = registro.spinner
            itemView.Txtvuelo.text = registro.txtvuelo
            itemView.Num_vuel.text = registro.num_vuel
            itemView.Num_asien.text = registro.num_asien
            itemView.Origen.text = registro.origen
            itemView.Spinner2.text = registro.spinner2
            itemView.Txtdestino.text = registro.txtdestino
            itemView.Spndestino.text = registro.spndestino



            itemView.UpdatedAt.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(registro.updatedAt).toString()
            itemView.setOnClickListener{ clickListener(registro)}
        }
    }
}