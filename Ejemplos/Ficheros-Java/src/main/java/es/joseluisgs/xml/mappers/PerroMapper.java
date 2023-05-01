package es.joseluisgs.xml.mappers;

import es.joseluisgs.xml.dto.PerroDto;
import es.joseluisgs.xml.dto.PerroListDto;
import es.joseluisgs.xml.model.Perro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PerroMapper {
    public static PerroDto toPerroDto(Perro perro) {
        return new PerroDto(
                perro.getUuid().toString(),
                perro.getNombre(),
                String.valueOf(perro.getEdad()),
                perro.getCreatedAt().toString()
        );
    }

    public static Perro toPerro(PerroDto perroDto) {
        return new Perro(
                UUID.fromString(perroDto.getUuid()),
                perroDto.getNombre(),
                Integer.parseInt(perroDto.getEdad()),
                LocalDateTime.parse(perroDto.getCreatedAt())
        );
    }

    public static List<Perro> toPerroList(PerroListDto perroDtoList) {
        return perroDtoList.perros.stream().map(PerroMapper::toPerro).toList();
    }

    public static PerroListDto toPerroListDto(List<Perro> perroList) {
        PerroListDto perroListDto = new PerroListDto();
        perroListDto.perros = perroList.stream().map(PerroMapper::toPerroDto).toList();
        return perroListDto;
    }
}
