package edu.veterinaria.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.veterinaria.R
import edu.veterinaria.model.Veterinario

class InformeAdapter(val lista: ArrayList<Veterinario>): RecyclerView.Adapter<InformeAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.informe_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = lista[position].id.toString()
        holder.causa.text=lista[position].causas
        holder.medicamento.text= lista[position].medicamentos
        holder.mascota.text= lista[position].mascota
        holder.medico.text= lista[position].medico
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: TextView
        var causa: TextView
        var medicamento: TextView
        var medico: TextView
        var mascota: TextView

        init {
            id = view.findViewById(R.id.i_id)
            causa = view.findViewById(R.id.i_causas)
            medicamento = view.findViewById(R.id.i_medicamentos)
            medico = view.findViewById(R.id.i_medico)
            mascota = view.findViewById(R.id.i_mascota)
        }

    }




}