package repositories

import Exceptions.PokedexFileNotFound
import Exceptions.PokedexParserError
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import models.Pokedex
import models.Pokemon
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

@OptIn(ExperimentalStdlibApi::class)
class PokedexRepository {
    // Lista de pokemons

    // Backin properties: https://kotlinlang.org/docs/properties.html#backing-properties
    private val _pokemons = mutableListOf<Pokemon>()
    val pokemons: List<Pokemon> get() = _pokemons

    init {
        logger.debug { "Inicializando Pokedex" }

        // Fichero JSON de Resources
        val pokemonFile = PokedexRepository::class.java.getResourceAsStream("/pokemon.json")
            ?: throw PokedexFileNotFound("Error al cargar el JSON o el fichero no existe")

        // Cargamos Moshi
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter<Pokedex>()
        val pokedex = jsonAdapter.fromJson(pokemonFile.bufferedReader().readText())
            ?: throw PokedexParserError("Error al parsear el JSON de Pokedex")


        // Cargamos los pokemons
        _pokemons.addAll(pokedex.pokemon)

        logger.debug { "Pokedex cargada con: ${pokemons.size} pokemons" }

    }

}