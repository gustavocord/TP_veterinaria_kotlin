package edu.veterinaria.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.veterinaria.model.Mascota
import edu.veterinaria.model.Veterinario
import java.lang.Exception

class DbHelper(
    context: Context, factory:
    SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    companion object {
        private val DATABASE_NAME = "veterinaria.db"
        private val DATABASE_VERSION = 1

        val TABLE_MASCOTAS = "mascotas"
        val COLUMN_ID = "id"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_TIPO = "tipo"
        val COLUMN_RAZA = "raza"
        val COLUMN_EDAD = "edad"
        val COLUMN_CAUSA = "causa_de_atencion"
        val COLUMN_VET = "medico"
        val COLUMN_FECHA="fecha"


        // tabla para resultados

        val TABLE_VETERINARIOS = "veterinario"
        val COLUMN_ID_R= "id"
        val COLUMN_CAUSA_R = "causa"
        val COLUMN_MEDICAMENTO = "medicamento"
        val COLUMN_MEDICO = "medico"
        val COLUMN_MASCOTA = "mascota"



    }


    override fun onCreate(db: SQLiteDatabase?) {

        var animal =
            ("CREATE TABLE " + TABLE_MASCOTAS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_TIPO + " TEXT, " +
                    COLUMN_RAZA + " TEXT, " +
                    COLUMN_EDAD + " TEXT, " +
                    COLUMN_VET + " TEXT, " +
                    COLUMN_FECHA + " TEXT, " +
                    COLUMN_CAUSA + " TEXT )")

        var veterinario =
            ("CREATE TABLE " + TABLE_VETERINARIOS + " ( " + COLUMN_ID_R + " INTEGER PRIMARY KEY, " +
                    COLUMN_CAUSA_R + " TEXT, " +
                    COLUMN_MEDICAMENTO + " TEXT, " +
                    COLUMN_MASCOTA+ " TEXT, " +
                    COLUMN_MEDICO + " TEXT )")


        db?.execSQL(veterinario)

       db?.execSQL(animal)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_MASCOTAS)
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_VETERINARIOS)

        onCreate(db)
    }


    fun saveMascota(mascota: Mascota): Boolean {
        try {

            // recuperamos el objeto db para escritura en la base de datos
            val db = this.writableDatabase


            // armo los valores que voy a insertar en mi tabla persona
            val values = ContentValues()

            values.put(COLUMN_NOMBRE, mascota.nombre)
            values.put(COLUMN_TIPO, mascota.tipo)
            values.put(COLUMN_RAZA, mascota.raza)
            values.put(COLUMN_EDAD, mascota.edad)
            values.put(COLUMN_CAUSA, mascota.causa)
            values.put(COLUMN_VET, mascota.medico)
            values.put(COLUMN_FECHA, mascota.fecha)


            // query insert

            db.insert(TABLE_MASCOTAS, null, values)

            return true
        } catch (e: Exception) {
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun getCountatention(medico: String): Boolean {


        val db = this.readableDatabase
        var med = "'"+medico+"'"
        val query = "SELECT * FROM " + TABLE_MASCOTAS + " WHERE "+ COLUMN_VET + " = " + med
        val cursor = db.rawQuery(query, null)


        return cursor.count<5
    }

    fun getAtencionpordia(fecha: String):Boolean{


        val db = this.readableDatabase
        var f = "'"+fecha+"'"
        val query = "SELECT * FROM " + TABLE_MASCOTAS + " WHERE "+ COLUMN_FECHA + " = " + f
        val cursor = db.rawQuery(query, null)


        return cursor.count<20


    }


    fun getAllMascotas(): ArrayList<Mascota> {

        val db = this.readableDatabase
        val listamascotas: ArrayList<Mascota> = ArrayList<Mascota>()
        val query = "SELECT * FROM " + TABLE_MASCOTAS

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val raza = cursor.getString(cursor.getColumnIndex(COLUMN_RAZA))
                val edad = cursor.getString(cursor.getColumnIndex(COLUMN_EDAD))
                val causa = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA))
                val veterinario = cursor.getString(cursor.getColumnIndex(COLUMN_VET))
                val fecha = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA))


                listamascotas.add(
                    Mascota(
                        id,
                        nombre,
                        tipo,
                        raza,
                        edad,
                        causa,
                        veterinario,
                        fecha

                    )
                )
            } while (cursor.moveToNext())

        }
        return listamascotas
    }



   /// RESULTADOS



    fun getAllResultados(): ArrayList<Veterinario> {

        val db = this.readableDatabase
        val listaResultados: ArrayList<Veterinario> = ArrayList<Veterinario>()
        val query = "SELECT * FROM " + TABLE_VETERINARIOS + " ORDER BY "+ COLUMN_MEDICO

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_R))
                val medicamento = cursor.getString(cursor.getColumnIndex(COLUMN_MEDICAMENTO))
                val causa = cursor.getString(cursor.getColumnIndex(COLUMN_CAUSA_R))
                val medico = cursor.getString(cursor.getColumnIndex(COLUMN_MEDICO))
                val mascota = cursor.getString(cursor.getColumnIndex(COLUMN_MASCOTA))



                listaResultados.add(
                    Veterinario(
                        id,
                        causa,
                        medicamento,
                        medico,
                        mascota

                    )
                )
            } while (cursor.moveToNext())

        }
        return listaResultados
    }




    fun saveResultado(veterinario: Veterinario): Boolean {
        try {

            // recuperamos el objeto db para escritura en la base de datos
            val db = this.writableDatabase


            // armo los valores que voy a insertar en mi tabla persona
            val values = ContentValues()

            values.put(COLUMN_MEDICAMENTO, veterinario.medicamentos)
            values.put(COLUMN_CAUSA_R, veterinario.causas)
            values.put(COLUMN_MEDICO, veterinario.medico)
            values.put(COLUMN_MASCOTA, veterinario.mascota)


            // query insert

            db.insert(TABLE_VETERINARIOS, null, values)

            return true
        } catch (e: Exception) {
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }


}