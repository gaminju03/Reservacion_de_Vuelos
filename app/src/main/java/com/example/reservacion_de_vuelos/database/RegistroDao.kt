package com.example.reservacion_de_vuelos.database

import androidx.room.*
@Dao

interface RegistroDao {

    @Query("select * from registro order by nombre")
    fun loadAll(): List<Registro>
    @Insert
    fun insert(alumno:Registro)
    @Update(onConflict= OnConflictStrategy.REPLACE)
    fun update(alumno:Registro)
    @Delete
    fun delete(alumno:Registro)
    @Query("DELETE FROM registro")
    fun deleteAll()
    @Query("SELECT * FROM registro WHERE id=:id")
    fun loadById(id:Long):Registro
}