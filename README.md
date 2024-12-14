# Gestión de Destinos Turísticos

Este proyecto es una aplicación desarrollada con **Spring Boot** para gestionar usuarios y destinos turísticos. Incluye la funcionalidad para asignar destinos a usuarios y generar facturas en formato PDF utilizando **Apache PDFBox**.

----------

## **Requisitos Previos**

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

-   **Java 17** o superior.

-   **IntelliJ IDEA** (versión Community o Ultimate).

-   **MySQL** y **MySQL Workbench**.

-   **Maven** (incluido con IntelliJ IDEA).

-   **Postman** u otra herramienta para probar APIs (opcional).


----------

## **Clonar el Proyecto y Configuración Inicial**

1.  **Clona el repositorio desde GitHub:**

    -   Abre **IntelliJ IDEA**.

    -   Ve a `File > New > Project from Version Control`.

    -   Pega la URL del repositorio y haz clic en `Clone`.

        ```
        https://github.com/tuusuario/tu-repositorio.git
        ```

2.  **Abre el proyecto en IntelliJ IDEA:**

    Una vez clonado, IntelliJ detectará automáticamente que el proyecto utiliza **Maven**. Si no lo hace:

    -   Ve a `File > Project Structure > Modules`.

    -   Asegúrate de que el módulo del proyecto esté configurado como **Maven Module**.

3.  **Actualiza las dependencias de Maven:**

    -   Abre el archivo `pom.xml`.

    -   Haz clic derecho en cualquier parte del archivo y selecciona **Reload Maven Project**.

    -   Verifica que todas las dependencias se descarguen correctamente.


----------

## **Configuración de la Base de Datos**

1.  **Crea la base de datos en MySQL Workbench:**

    -   Abre **MySQL Workbench**.

    -   Ejecuta el siguiente comando para crear la base de datos:

        ```
        CREATE DATABASE gestion_destinos;
        ```

2.  **Configura las credenciales de MySQL en** `**application.properties**`**:**

    Abre el archivo `src/main/resources/application.properties` y configura las credenciales de conexión:

    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/gestion_destinos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    
    # Configuración adicional
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    ```

    -   Cambia `tu_usuario` y `tu_contraseña` por tus credenciales de MySQL.

3.  **Verifica la conexión a la base de datos:**

    -   Una vez configurado, ejecuta el proyecto en IntelliJ (`Run > Run 'GestionDestinosApplication'`).

    -   Las tablas se crearán automáticamente en la base de datos `gestion_destinos`.


----------

## **Puesta en Marcha**

1.  **Ejecuta el Proyecto:**

    -   Ve al menú superior de IntelliJ y selecciona `Run > Run 'GestionDestinosApplication'`.

    -   Asegúrate de que no haya errores en la consola.

    -   La aplicación estará disponible en:

        ```
        http://localhost:8080
        ```

2.  **Prueba los Endpoints:**

    Utiliza una herramienta como **Postman** para interactuar con la API. A continuación, se detallan las pruebas de los endpoints disponibles.


----------

## **Guía de Pruebas de Endpoints**

### **Usuarios**

#### **1. Crear un usuario**

-   **Endpoint:**  `POST /usuarios`

-   **Body JSON:**

    ```
    {
      "nombre": "Juan Pérez",
      "email": "juan.perez@example.com"
    }
    ```

-   **Respuesta esperada:**

    ```
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "email": "juan.perez@example.com",
      "destinosAsignados": []
    }
    ```


#### **2. Obtener todos los usuarios**

-   **Endpoint:**  `GET /usuarios`

-   **Respuesta esperada:**

    ```
    [
      {
        "id": 1,
        "nombre": "Juan Pérez",
        "email": "juan.perez@example.com",
        "destinosAsignados": []
      }
    ]
    ```


#### **3. Asignar un destino a un usuario**

-   **Endpoint:**  `PUT /usuarios/{usuarioId}/destinos/{destinoId}`

-   **Ejemplo de URL:**  `PUT /usuarios/1/destinos/2`

-   **Respuesta esperada:**

    ```
    {
      "id": 1,
      "nombre": "Juan Pérez",
      "email": "juan.perez@example.com",
      "destinosAsignados": [
        {
          "id": 2,
          "nombre": "Cancún",
          "precio": 1500.00
        }
      ]
    }
    ```


#### **4. Generar factura para un usuario**

-   **Endpoint:**  `GET /usuarios/{usuarioId}/factura`

-   **Ejemplo de URL:**  `GET /usuarios/1/factura`

-   **Resultado:**

    -   Se descargará un archivo PDF con la factura del usuario que incluye:

        -   Nombre del usuario.

        -   Email.

        -   Lista de destinos asignados con precios.

        -   Total acumulado.


----------

### **Destinos Turísticos**

#### **1. Crear un destino**

-   **Endpoint:**  `POST /destinos`

-   **Body JSON:**

    ```
    {
      "nombre": "Playa del Carmen",
      "precio": 1200.50
    }
    ```

-   **Respuesta esperada:**

    ```
    {
      "id": 2,
      "nombre": "Playa del Carmen",
      "precio": 1200.50
    }
    ```


#### **2. Obtener todos los destinos**

-   **Endpoint:**  `GET /destinos`

-   **Respuesta esperada:**

    ```
    [
      {
        "id": 2,
        "nombre": "Playa del Carmen",
        "precio": 1200.50
      }
    ]
    ```


----------

## **Estructura del Proyecto**

```
src
├── main
│   ├── java
│   │   └── com.turismo.gestion_destinos
│   │       ├── controller
│   │       ├── model
│   │       ├── repository
│   │       └── service
│   └── resources
│       ├── application.properties
│       └── static
└── test
```

----------

## **Solución de Problemas**

1.  **No se crean las tablas en la base de datos:**

    -   Asegúrate de que `spring.jpa.hibernate.ddl-auto=update` esté configurado en `application.properties`.

    -   Verifica que la base de datos `gestion_destinos` exista en MySQL Workbench.

2.  **Error de conexión a la base de datos:**

    -   Verifica que el servicio MySQL esté en ejecución.

    -   Asegúrate de que las credenciales en `application.properties` sean correctas.

3.  **Problema con dependencias de Maven:**

    -   Haz clic en `File > Invalidate Caches / Restart` en IntelliJ.

    -   Actualiza Maven con **Reload Maven Project**.