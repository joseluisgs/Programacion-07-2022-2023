package es.joseluisgs.xml.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "perro")
public class PerroDto {
    @Attribute
    private String uuid;
    @Element
    private String nombre;
    @Element
    private String edad;
    @Element(name = "created_at", required = false)
    private String createdAt;
}



