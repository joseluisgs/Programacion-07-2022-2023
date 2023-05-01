package es.joseluisgs.xml;

import es.joseluisgs.xml.dto.PerroDto;
import es.joseluisgs.xml.dto.PerroListDto;
import es.joseluisgs.xml.mappers.PerroMapper;
import es.joseluisgs.xml.model.Perro;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.List;

public class PerroXml {
    public static void main(String[] args) {
        System.out.println("Hola XML");
        String path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "perro.xml";

        var perro = new Perro("Firulais", 5);
        System.out.println(perro);

        Serializer serializer = new Persister();
        try {
            serializer.write(PerroMapper.toPerroDto(perro), new File(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        try {
            var dto = serializer.read(PerroDto.class, new File(path));
            System.out.println(dto);
            var perro2 = PerroMapper.toPerro(dto);
            System.out.println(perro2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        path = System.getProperty("user.dir") + File.separator + "data" + File.separator + "perros.xml";

        var perros = List.of(new Perro("Firulais", 5), new Perro("Salchichon", 6), new Perro("Chispas", 7));

        try {
            serializer.write(PerroMapper.toPerroListDto(perros), new File(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            var dtoList = serializer.read(PerroListDto.class, new File(path));
            var perros2 = PerroMapper.toPerroList(dtoList);
            System.out.println(perros2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
