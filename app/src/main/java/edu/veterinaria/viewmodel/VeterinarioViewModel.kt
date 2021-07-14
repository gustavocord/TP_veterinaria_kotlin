package edu.veterinaria.viewmodel

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.ViewModel
import edu.veterinaria.dao.DbHelper
import edu.veterinaria.model.Veterinario

class VeterinarioViewModel : ViewModel() {

   fun saveResultado(
        causa: String,
        medicamento: String,
        medico: String,
        mascota:String,
        context: Context
    ): Boolean {

        val db: DbHelper = DbHelper(context, null)

        return db.saveResultado(Veterinario(0,causa, medicamento,medico,mascota))
    }


    fun getAllResultado(context: Context):ArrayList<Veterinario>{
        val db: DbHelper = DbHelper(context, null)

        return db.getAllResultados()
    }

    fun verificar(causa : EditText, medicamento:EditText):Boolean{


        return (causa.text.toString()!=""&& medicamento.text.toString()!=""
               )

    }

}