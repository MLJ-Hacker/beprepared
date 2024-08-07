package com.mmj.beprepared.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "BePrepared",
                description = "Uma app que permite os usuários se manterem informados e seguros  durante desastres naturais.",
                version = "1.0",
                contact = @Contact(
                        name = "Max Macamo",
                        email = "maxmacamo07@gmail.com",
                        url = "https://github.com/MLJ-Hacker"
                ),
                license = @License(
                        name = "Apache License 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)@SecurityScheme(
        name =  "Bearer Authentication",
        description = "Faça o login na API, para poder usar perfeitamente a aplicação BePreapared, "+
                "coloque o seu token de authenticação no campo abaixo e clique no botão authorize",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
