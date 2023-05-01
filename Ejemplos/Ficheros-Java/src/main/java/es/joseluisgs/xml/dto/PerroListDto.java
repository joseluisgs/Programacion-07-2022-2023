package es.joseluisgs.xml.dto;

// Para la lista
// Para las listas, necesitamos una clase contenedora

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "perros")
public class PerroListDto {
    @ElementList(name = "perros", inline = true) // inline para que no se cree un elemento contenedor
    public List<PerroDto> perros;
}
