package com.upiiz.documentacion.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Soporte EAM",
                description = "Documentación de endpoints para la gestión de tickets de soporte técnico.",
                version = "1.0.0", // Versión inicial[cite: 1]
                contact = @Contact(
                        name = "Federico Sánchez Ibarra",
                        email = "federico.sanchez@example.com",
                        url = "https://github.com/tu-usuario"
                ),
                license = @License(
                        name = "Licencia Estudiantil UPIIZ",
                        url = "http://www.upiiz.ipn.mx"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor Local de Pruebas"),
                @Server(url = "https://tu-servicio-api.onrender.com", description = "Servidor de Producción (Render)")
        }
)
public class SwaggerConfig {
}