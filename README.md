# CASO "BACKEND-BASIC CHALLENGE"

## Desarrollar una API con las siguientes consideraciones:

### Consideraciones técnicas:

- El lenguaje de programación a utilizar es Java con el framework Spring Boot.
- Utilizar programación reactiva RxJava

### Funcionalidades Requeridas:

- Se debe crear una API para aplicar un tipo de cambio a un monto.
- La API debe recibir el valor **"monto"**, **"moneda origen"**, **"moneda destino"** y devolver el **"monto"**, **"monto con tipo de cambio"**, **"moneda origen"**, **"moneda destino"** y **"tipo de cambio"**.
- Se debe crear la información del tipo de cambio en una in memory database, por ejemplo H2.
- Dockerizar el JAR e invocar a la API desde el contenedor. (Windows o CentOS)
- El uso de la API debe ser mostrada desde Postman.

### Funcionalidades Opcionales:

- Implementar un nivel de seguridad en la consulta (JWT).
- Crear un POST para actualizar el valor del tipo de cambio.
- Implementar un front con angular que consuma la API.
- Trabajar en las ramas como si fuera un caso de DevSecOps (con definición de actividades)

### Consideraciones:

- La elección de la Imagen -- al dockerizar -- es libre.
- No hay un standard en los nombres de los objetos creados.
- En caso de alguna duda en el enunciado, tomar una decisión y luego sustentarla durante la entrevista.

### Nota:

- Cuando termines de desarrollar el código API, debes ingresar el link en el formulario que se encuentra en la parte superior derecha.