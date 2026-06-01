# pesaje-servicio

Microservicio de gestión de pesajes para la plataforma Karübag.

## Descripción
Registra los kilos de materiales reciclables por tipo en cada retiro. Se comunica con material-servicio para obtener el precio por kilo y calcular el total automáticamente.

## Tecnologías
- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- PostgreSQL (Neon)
- WebClient (Spring WebFlux)

## Puerto
`8088`

## Base de datos
`karubag_pesaje`

## Comunicación con otros servicios
- `material-servicio` (:8083) — obtiene precio por kilo del material

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/pesajes | Listar todos los pesajes |
| GET | /api/pesajes/retiro/{retiroId} | Listar por retiro |
| GET | /api/pesajes/material/{materialId} | Listar por material |
| GET | /api/pesajes/retiro/{retiroId}/total-kilos | Total kilos por retiro |
| GET | /api/pesajes/material/{materialId}/total-kilos | Total kilos por material |
| GET | /api/pesajes/{id} | Obtener pesaje por ID |
| POST | /api/pesajes | Crear pesaje |
| PUT | /api/pesajes/{id} | Actualizar pesaje |
| DELETE | /api/pesajes/{id} | Eliminar pesaje |

## Cómo ejecutar
```bash
./mvnw spring-boot:run
```

## Variables de entorno
```
spring.datasource.url=jdbc:postgresql://<host>/karubag_pesaje
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
```