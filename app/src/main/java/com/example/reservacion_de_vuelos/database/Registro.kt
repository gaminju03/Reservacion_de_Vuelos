package com.example.reservacion_de_vuelos.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName="registro")

data class Registro (
    @PrimaryKey(autoGenerate=true)
    var id:Long=0,
    var nom_pas:String="",
    var prec_bol:String="",
 //   var spinner:String="tarjeta",
    var cod_vuel:String="",
    var num_vuel:String="",
    var num_asien:String="",
   // var origen:String="",
 //   var spinner2:String="mexico",
 //   var spndestino:String="japon",


    @ColumnInfo(name="updated_at")
var updatedAt: Date

)



