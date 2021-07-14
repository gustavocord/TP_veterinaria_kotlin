package edu.veterinaria.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.veterinaria.R
import edu.veterinaria.viewmodel.InformeAdapter
import edu.veterinaria.viewmodel.MascotaViewModel
import edu.veterinaria.viewmodel.VeterinarioViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var ingreso : Button
    lateinit var bInfrome : Button
    lateinit var rv_informe: RecyclerView
    val sdf = SimpleDateFormat("dd/M/yyyy")
    val fecha = sdf.format(Date())

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeElements()



        val veterinarioVM = ViewModelProvider(this).get(VeterinarioViewModel::class.java)
        val mascotaVM = ViewModelProvider(this).get(MascotaViewModel::class.java)




        ingreso.setOnClickListener {

            if (mascotaVM.getAtencionpordia(this, fecha)) {
                val intent: Intent = Intent(this, MascotaActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(
                    this,
                    "No hay lugar disponible para la fecha",
                    android.widget.Toast.LENGTH_LONG
                )
                    .show()
            }
        }

        bInfrome.setOnClickListener{
            rv_informe.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)


            rv_informe.adapter = InformeAdapter(veterinarioVM.getAllResultado(this))
        }


    }



    private fun initializeElements() {

        ingreso = findViewById(R.id.b_ingresar)
        bInfrome = findViewById(R.id.b_informe)
        rv_informe= findViewById(R.id.rv_informe)

    }
}