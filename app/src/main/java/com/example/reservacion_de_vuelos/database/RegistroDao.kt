package com.example.reservacion_de_vuelos.database

import androidx.room.*
@Dao

interface RegistroDao {

    @Query("select * from registro order by nom_pas")
    fun loadAll(): List<Registro>
    @Insert
    fun insert(registro: Registro)
    @Update(onConflict= OnConflictStrategy.REPLACE)
    fun update(registro:Registro)
    @Delete
    fun delete(registro:Registro)
    @Query("DELETE FROM registro")
    fun deleteAll()
    @Query("SELECT * FROM registro WHERE id=:id")
    fun loadById(id:Long):Registro
}