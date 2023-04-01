package com.tienda.clientes.documentation;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Clientes API",
                version = "1.0",
                description = "API encargada de dar altas, bajas, eliminar y modificar los clientes de una BD.",
                license = @License(name = "Apache 2.0", url = "http://foo.bar"),
                contact = @Contact(url = "https://github.com/DiegoRSX/", name = "Diego", email = "diegor922345@gmail.com")
        )
)
public class DocumentationConfig {

}
