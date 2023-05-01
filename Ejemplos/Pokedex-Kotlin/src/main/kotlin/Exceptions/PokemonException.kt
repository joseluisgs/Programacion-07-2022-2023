package Exceptions

sealed class PokedexException(message: String) : Exception(message)
class PokemonNotFound(message: String) : PokedexException(message)
class PokemonAlreadyExists(message: String) : PokedexException(message)
class PokemonNotValid(message: String) : PokedexException(message)
class PokemonInvalid(message: String) : PokedexException(message)

class PokedexFileNotFound(message: String) : PokedexException(message)
class PokedexParserError(message: String) : PokedexException(message)
