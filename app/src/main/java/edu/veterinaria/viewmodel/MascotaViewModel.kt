package edu.veterinaria.viewmodel

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import edu.veterinaria.dao.DbHelper
import edu.veterinaria.model.Mascota

class MascotaViewModel : ViewModel(){



    fun saveMascota(
        nombre: String,
        edad: String,
        causa: String,
        tipo: String,
        raza: String,
        medico: String,
        fecha: String,
        context:Context
    ): Boolean {

        val db: DbHelper = DbHelper(context, null)

        return db.saveMascota(Mascota(0,nombre, tipo,raza ,edad,causa,medico,fecha))
    }


    fun getCountatention(context: Context ,medico: String):Boolean{
        val db: DbHelper = DbHelper(context, null)

        return db.getCountatention(medico)
    }


    fun getAllMascotas(context: Context):ArrayList<Mascota>{
        val db: DbHelper = DbHelper(context, null)

        return db.getAllMascotas()
    }


    fun verificar( nombre :EditText, edad :EditText, raza:EditText, causa:EditText ):Boolean{


        return (nombre.text.toString() != "" && edad.text.toString() != "" && raza.text.toString() != ""
                && causa.text.toString() != "")

    }


    fun getAtencionpordia(context: Context,fecha: String):Boolean{
        val db: DbHelper = DbHelper(context, null)

        return db.getAtencionpordia(fecha)
    }


}