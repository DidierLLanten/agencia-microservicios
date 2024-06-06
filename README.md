# Descripcion
API para la gestion de una agencia de viajes, permite gestionar reservas, alojamientos, destinos, vuelos y usuarios.

# Pasos para correr el proyecto

Hay dos ramas funcionales 
- main
- config-server (Esta esta necesita ademas el repositorio https://github.com/DidierLLanten/config-service)

1. Compilar cada microservicio 
 - Lifecycle
    - clean
    - install

2. Ejecutar el comando en la raiz del proyecto
    ```bash 
    docker-compose up 
    ```

   ## Links 
   - http://localhost:8761/
   - http://keycloak:9090/
 

3. En la raiz del proyecto se encuentran los archivos para ejecutar las peticiones en POSTMAN 

## DEPENDENCIAS
   - Spring Cloud Netflix Eureka
   - Spring Cloud Gateway
   - RabbitMQ
   - Keycloak
    