package org.springboot.jpa.santiago.backendchronoturner;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
            title = "Chrono-Turner API",
            description = "Turning chaos into clarity",
            termsOfService = "https://www.chrono-turner.com/terms-and-conditions",  //Una página completamente inventada
            version = "1.0.0",   //Según el estándar de versionamiento de aplicaciones
            contact = @Contact(
                name = "Chrono-Turner",
                url = "https://www.chrono-turner.com/contact",  //Una página completamente inventada
                email = "pepitoperez@chronoturner.com"
            ),
            license = @License(
                name = "Standard software use for Chrono-Turner",
                url = "https://www.chrono-turner.com/licence"  //Una página completamente inventada
            )
        ),
        servers = {
            @Server(
                description = "DEV_SERVER",
                url = "http://18.191.247.216:8080" /*"https://localhost:8080"*/
            ),
                @Server(
                description = "PROD_SERVER",
                url = "https://www.chrono-turner.com"
            ),
        },
        security = @SecurityRequirement(
            name = "Security token" //Debe ser el mismo nombre del esquema de seguridad, para que deje trabajar los endpoints con el token
        )
)
@SecurityScheme(
    name = "Security token",    //Para obtener el token, debo, o registrarme, o iniciar sesión
    description = "Access token for Chrono-Turner API",
    type = SecuritySchemeType.HTTP,  //Para trabajar con token
    paramName = HttpHeaders.AUTHORIZATION,
    in = SecuritySchemeIn.HEADER,
    scheme = "Bearer",  //Si algo, poner en minúsculas
    bearerFormat = "JWT"
)
public class SwaggerConfig {
    //Atributos de SwaggerConfig
    //Constructores de SwaggerConfig
    //Asignadores de atributos de SwaggerConfig (setters)
    //Lectores de atributos de SwaggerConfig (getters)
    //Métodos de SwaggerConfig
}
