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

        ///registro = nombre de la tabla  y Registro = Nombre de la clase
        fun bind (registro: Registro, context: Context, clickListener: (Registro) -> Unit){

            itemView.NombrePass.text = registro.nom_pas
            itemView.Prec_bol.text = registro.prec_bol
            //itemView.Spinner.text = registro.spinner
            itemView.Txtvuelo.text = registro.cod_vuel
            itemView.Num_vuel.text = registro.num_vuel
            itemView.Num_asien.text = registro.num_asien
           // itemView.Origen.text = registro.origen
        //    itemView.Spinner2.text = registro.spinner2
        //    itemView.Spndestino.text = registro.spndestino
            //trea las variables de dia mes año
            itemView.UpdatedAt.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(registro.updatedAt).toString()
            itemView.setOnClickListener{ clickListener(registro)}
        }
    }
}