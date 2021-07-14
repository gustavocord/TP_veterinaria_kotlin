package edu.veterinaria.model


import java.io.Serializable

data class Mascota(var id: Int=0,val nombre: String, val tipo:String,
                   val raza:String, val edad:String, val causa:String , val medico: String , val fecha : String) : Serializable