package edu.veterinaria.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import edu.veterinaria.R
import edu.veterinaria.viewmodel.MascotaViewModel
import java.text.SimpleDateFormat
import java.util.*

class MascotaActivity : AppCompatActivity() {

    lateinit var id: TextView
    lateinit var nombre: EditText
    lateinit var raza: EditText
    lateinit var tipo: Spinner
    lateinit var edad: EditText
    lateinit var causa: EditText
    lateinit var registrar: Button
    lateinit var medicos: Spinner
    val sdf = SimpleDateFormat("dd/M/yyyy")
    val fecha = sdf.format(Date())
    var listaTipo = listOf("Gato", "Perro" ,"Conejo")
    var listaMedicos = listOf("Juan", "Nestor" ,"Roberto", "Ayelen" , "Estefania")





    //lateinit var mascotaVM: MascotaViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingreso_layout)
        inicializar()
        inicializarSpinner()


        var m : String = ""
        medicos.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                m=listaMedicos[position]

            }
        }


        var mascotaVM = ViewModelProvider(this).get(MascotaViewModel::class.java)



// ver esta parte que aun no se soluciona


        registrar.setOnClickListener(

            View.OnClickListener {

                if (mascotaVM.verificar(nombre, edad, raza, causa)) {

                    if (mascotaVM.getCountatention(this, m)) {


                        mascotaVM.saveMascota(
                            nombre.text.toString(),
                            edad.text.toString(),
                            raza.text.toString(),
                            tipo.selectedItem.toString(),
                            causa.text.toString(),
                            m,
                            fecha,
                            it.context
                        )
                        val intent: Intent = Intent(this, VeterinarioActivity::class.java)
                        intent.putExtra("medico", m)
                        intent.putExtra("mascota", nombre.text.toString())
                        startActivity(intent)


                    } else {

                        Toast.makeText(
                            this,
                            "el veterinario ya atendio suficiente",
                            android.widget.Toast.LENGTH_LONG
                        )
                            .show()

                    }

                } else {

                    Toast.makeText(
                        this,
                        "ingreso uno o mas valores vacio",
                        android.widget.Toast.LENGTH_LONG
                    )
                        .show()

                }


            })
    }


    private fun inicializar() {
        nombre = findViewById(R.id.m_nombre)
        raza = findViewById(R.id.m_raza)
        tipo = findViewById(R.id.m_tipo)
        edad = findViewById(R.id.m_edad)
        id = findViewById(R.id.m_id)
        registrar = findViewById(R.id.m_registrar)
        causa = findViewById(R.id.m_causa)
        medicos= findViewById(R.id.m_medico)

    }

    private fun inicializarSpinner()
    {

        tipo.adapter = ArrayAdapter(
            this,android.R.layout.simple_spinner_item,listaTipo
        )
        medicos.adapter = ArrayAdapter(
            this,android.R.layout.simple_spinner_item,listaMedicos
        )

    }




}