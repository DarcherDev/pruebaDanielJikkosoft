# Prueba Jikkosoft
@DarcherDev

Prueba para ingreso a Jikkosoft:

1. Diseño de una base de datos para blogs.
2. Función para retornar índices de dos números que, sumados, den el entero que pide la función. Este punto fue hecho en Java.
3. Diseño de backend de biblioteca. Este proyecto funciona con JDK 21, Spring Boot 3.2.0 y usa Maven como manejador de dependencias. 

   Se utiliza Docker para crear solo la imagen de MySQL. El proyecto debe compilarse y ejecutarse manualmente. En la carpeta `biblioteca` se deja el archivo JSON `Biblioteca.postman_collection.json` para importar la colección de la API en Postman.

## Pasos para levantar la imagen de MySQL con Docker

1. Clonar el proyecto.
2. Ejecutar ```mvn clean package```
3. Levantar Docker Compose con el comando:  
   ```bash
   docker compose up -d
