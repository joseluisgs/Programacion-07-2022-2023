import repositories.PokedexRepository

fun main(args: Array<String>) {
    println("!Pokemon!")

    val pokedex = PokedexRepository()

    // Ahora jugamos con las consultas

    // Obtener el nombre los 10 primeros pokemons
    println("Los 10 primeros pokemons son:")
    pokedex.pokemons.take(10).mapIndexed { index, pokemon -> println("${index + 1}. ${pokemon.name}") }

    // Obtener el nombre de los 5 ultimos pokemons
    println()
    println("Los 5 ultimos pokemons son:")
    pokedex.pokemons.takeLast(5).mapIndexed { index, pokemon -> println("${index + 1}. ${pokemon.name}") }

    // Obtener los datos de Pikachu
    println()
    println("Datos de Pikachu:")
    pokedex.pokemons.find { it.name == "Pikachu" }?.let { println(it) }

    // Obtener la evolucion de Charmander
    println()
    println("Evolucion de Charmander:")
    pokedex.pokemons.find { it.name == "Charmander" }?.let { println(it.nextEvolution?.map { p -> p.name }) }

    // Obtener el nombre de los pokemons de tipo fire
    println()
    println("Pokemons de tipo fire:")
    println(pokedex.pokemons.filter { it.type.contains("Fire") }.map { it.name }.toString())

    // Obtener el nombre de los pokemons con debilidad water o electric
    println()
    println("Pokemons con debilidad water o electric:")
    println(pokedex.pokemons.filter { it.weaknesses.contains("Water") || it.weaknesses.contains("Electric") }
        .map { it.name }.toString())

    // Numero de pokemons con solo una debilidad
    println()
    println("Numero de pokemons con solo una debilidad:")
    println(pokedex.pokemons.filter { it.weaknesses.size == 1 }.count())

    // Pokemon con mas debilidades
    println()
    println("Pokemon con mas debilidades:")
    println(pokedex.pokemons.maxByOrNull { it.weaknesses.size }?.name)

    // Pokemon con menos evoluciones
    println()
    println("Pokemon con menos evoluciones:")
    println(pokedex.pokemons.minByOrNull { it.nextEvolution?.size ?: 0 }?.name)

    // Pokemon con una evolucion que no es de tipo fire
    println()
    println("Pokemon con solo una evolución y es de tipo Electric:")
    println(pokedex.pokemons.find { (it.nextEvolution?.size == 1) && (it.type.contains("Electric")) }?.name)

    // Pokemon más pesado
    println()
    println("Pokemon más pesado:")
    println(pokedex.pokemons.maxByOrNull { it.weight }?.name)

    // Pokemon más alto
    println()
    println("Pokemon más alto:")
    println(pokedex.pokemons.maxByOrNull { it.height }?.name)

    // Pokemon con el nombre mas largo
    println()
    println("Pokemon con el nombre mas largo:")
    println(pokedex.pokemons.maxByOrNull { it.name.length }?.name)

    // Media de peso de los pokemons
    println()
    println("Media de peso de los pokemons:")
    println(pokedex.pokemons.map { it.weight.removeSuffix(" kg").toDouble() }.average())

    // Media de altura de los pokemons
    println()
    println("Media de altura de los pokemons:")
    println(pokedex.pokemons.map { it.height.removeSuffix(" m").toDouble() }.average())

    // Media de evoluciones de los pokemons
    println()
    println("Media de evoluciones de los pokemons:")
    println(pokedex.pokemons.map { it.nextEvolution?.size ?: 0 }.average())

    // Media de debilidades de los pokemons
    println()
    println("Media de debilidades de los pokemons:")
    println(pokedex.pokemons.map { it.weaknesses.size }.average())

    // Pokemons agrupados por tipo
    println()
    println("Pokemons agrupados por tipo:")
    println(pokedex.pokemons.groupBy { it.type }
        .map { "${it.key} ${it.value.map { p -> p.name }}" }
        .joinToString()
    )

    // Numero de pokemons agrupados por debilidad
    println()
    println("Numero de pokemons agrupados por debilidad:")
    println(pokedex.pokemons.flatMap { it.weaknesses }
        .groupBy { it }
        .mapValues { it.value.size }
    )

    // Pokemons agrupados por numero de evoluciones
    println()
    println("Pokemons agrupados por numero de evoluciones:")
    println(pokedex.pokemons.groupBy { it.nextEvolution?.size ?: 0 }
        .map { "${it.key} ${it.value.map { p -> p.name }}" }
        .joinToString()
    )

    // Sacar la debilidad más común
    println()
    println("Debilidad más común:")
    println(pokedex.pokemons.flatMap { it.weaknesses }
        .groupBy { it }
        .mapValues { it.value.size }
        .maxByOrNull { it.value }
    )
    //Sacar una lista de los pokemons ordenador por altura
    println()
    println("Lista de los pokemons ordenador por altura:")
    pokedex.pokemons.toList().sortedByDescending { it.height }.forEach { println("Id: ${it.id}, Nombre: ${it.name}, Altura: ${it.height}") }

    //El pokemon que tiene más posibilidades de aparecer
    println()
    println("El pokemon que tiene más posibilidades de aparecer:")
    println(pokedex.pokemons.maxByOrNull { it.spawnChance }?.name)

    //El pokemon que tiene menos posibilidades de aparecer
    println()
    println("El pokemon que tiene menos posibilidades de aparecer:")
    println(pokedex.pokemons.minByOrNull { it.spawnChance }?.name)

    //La media de posibilidad de aparición de los pokemons
    println()
    println("La media de posibilidad de aparición de los pokemons:")
    println(pokedex.pokemons.map { it.spawnChance }.average())

}