package  com.example.reservacion_de_vuelos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.listaalumnos.helper.DateConverter
import com.example.reservacion_de_vuelos.database.Registro
import com.example.reservacion_de_vuelos.database.RegistroDao

@Database(entities =arrayOf(Registro::class),version=1,exportSchema=false )
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        private var Instance:AppDatabase?=null
        fun  getInstance(context: Context):AppDatabase?{
            if (Instance==null){
                synchronized(AppDatabase::class){
                    Instance= Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "registro5.db"
                    ).build()
                }
            }
            return Instance
        }
    }
    abstract fun RegistroDao():RegistroDao

}