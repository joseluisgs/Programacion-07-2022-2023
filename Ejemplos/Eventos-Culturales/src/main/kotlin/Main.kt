import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Evento
import models.EventoData
import java.io.File
import java.nio.charset.Charset

@ExperimentalStdlibApi
fun main(args: Array<String>) {
    val eventos = leerFicheroJson()

    println("Datos cargados: ${eventos.size}")

    // los 10 primeros eventos
    println()
    println("Los 10 primeros eventos")
    eventos.take(10).forEachIndexed() { index, evento ->
        println("Evento ${index + 1}: ${evento.title}")
    }

    // Los 10 últimos eventos
    println()
    println("Los 10 últimos eventos")
    eventos.takeLast(10).forEachIndexed() { index, evento ->
        println("Evento ${eventos.size - index}: ${evento.title}")
    }

    // Los 10 primeros eventos gratis
    println()
    println("Los 10 primeros eventos gratis")
    eventos.filter { it.free == 1 }.take(10).forEachIndexed() { index, evento ->
        println("Evento ${index + 1}: ${evento.title}")
    }

    // Eventos que tienen un precio
    println()
    println("Eventos que tienen un precio")
    eventos.filter { it.price != "" }.forEachIndexed() { index, evento ->
        println("Evento ${index + 1}: ${evento.title} - ${evento.price}")
    }

    // Numero de eventos que son gratis
    println()
    println("Numero de eventos que son gratis")
    println(eventos.count { it.free == 1 })

    // Numero de eventos que son de pago
    println()
    println("Numero de eventos que son de pago")
    println(eventos.count { it.free == 0 })

    // media de precio de los eventos que está indicado euro
    println()
    println("media de precio de los eventos que está indicado correctamente")
    eventos.filter { it.price.contains("euros")
            && !it.price.contains("persona")
            && !it.price.contains("De")
            && !it.price.contains("Entrada")
            && !it.price.contains("Platea")
    }.map {
        it.price.substring(0, it.price.indexOf("euros"))
            .trim()
            .replace(",",".")
            .toDouble() }
        .average()
        .let { println(it) }

    // Agrupados por codigo postal
    println()
    println("Agrupados por codigo postal")
    eventos.groupBy { it.address?.area?.postalCode }
        .forEach { (key, value) ->
            println("Codigo postal: $key - Eventos: ${value.map { it.title }}")
        }

    // Numero de eventos por codigo postal
    println()
    println("Numero de eventos por codigo postal")
    eventos.groupBy { it.address?.area?.postalCode }
        .forEach { (key, value) ->
            println("Codigo postal: $key - Eventos: ${value.size}")
        }

    // Distrito que tiene más eventos
    println()
    println("Distrito que tiene más eventos")
    val res01 = eventos.groupBy { it.address?.area?.postalCode }
        .maxByOrNull { it.value.size }
    println("Codigo postal: ${res01?.key} - Eventos: ${res01?.value?.size}")

    // Nombre del distrito que tiene más eventos
    println()
    println("Nombre del distrito que tiene más eventos")
    val res02 = eventos.filter { it.address?.area?.postalCode == res01?.key }.map { it.address?.district?.id?.removePrefix("https://datos.madrid.es/egob/kos/Provincia/Madrid/Municipio/Madrid/Distrito/") }.distinct()
    println("Distrito: ${res02.first()}")


}

@ExperimentalStdlibApi
fun leerFicheroJson(): List<EventoData> {
    val jsonFile = ClassLoader.getSystemResource("206974-0-agenda-eventos-culturales-100.json").file
        ?: throw IllegalArgumentException("Fichero no encontrado")

    println(jsonFile)

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val data = moshi.adapter<Evento>().fromJson(File(jsonFile).readText(Charset.defaultCharset())) ?: throw IllegalArgumentException("Error al leer el fichero")
    //val data = moshi.adapter<Evento>().fromJson(File(jsonFile).inputStream().buffered().reader().readText())
    return data.eventoData
}

