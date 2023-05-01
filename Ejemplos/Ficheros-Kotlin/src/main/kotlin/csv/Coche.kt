package csv

import java.time.LocalDate

data class Coche(val id: Int = contador++, val marca: String, val precio: Double, val fechaLanzamiento: LocalDate) {

    companion object {
        var contador = 1
        fun randomCoche() =
            Coche(marca = "Marca $contador", precio = Math.random() * 10000, fechaLanzamiento = LocalDate.now())
    }

    override fun toString() = "Coche(id=$id, marca='$marca', precio=$precio, fechaLanzamiento=$fechaLanzamiento)"
}