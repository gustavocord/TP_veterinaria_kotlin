package edu.veterinaria.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import edu.veterinaria.R
import edu.veterinaria.viewmodel.VeterinarioViewModel

class VeterinarioActivity : AppCompatActivity() {

    lateinit var causas : EditText
    lateinit var medicamentos: EditText
    lateinit var guardar : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resulatado_layout)

        val medico = intent.getSerializableExtra("medico") as String
        val mascota = intent.getSerializableExtra("mascota") as String

        inicializar()


        var veterinarioVm = ViewModelProvider(this).get(VeterinarioViewModel::class.java)




        guardar.setOnClickListener {

            //if (veterinarioVm.verificar(causas, medicamentos)) {
            veterinarioVm.saveResultado(
                causas.text.toString(),
                medicamentos.text.toString(),
                medico,
                mascota,
                it.context
            )

            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }

    }

    private fun inicializar(){
        causas = findViewById(R.id.e_causa)
        medicamentos = findViewById(R.id.e_medicamentos)
        guardar = findViewById(R.id.b_revision)
    }


}