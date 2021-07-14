package edu.veterinaria.model

import java.io.Serializable

data class Veterinario(var id: Int=0, val causas: String, val medicamentos: String, val medico : String, val mascota: String): Serializable
