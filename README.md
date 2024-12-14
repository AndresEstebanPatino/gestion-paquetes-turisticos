# Gestión de Destinos Turísticos

Este proyecto es una aplicación desarrollada con **Spring Boot** para gestionar usuarios y destinos turísticos. Incluye la funcionalidad para asignar destinos a usuarios y generar facturas en formato PDF utilizando **Apache PDFBox**.

---

## **Requisitos Previos**

Antes de comenzar, asegúrate de tener instalados los siguientes programas:

- **Java 17** o superior.
- **IntelliJ IDEA** (versión Community o Ultimate).
- **MySQL** y **MySQL Workbench**.
- **Maven** (incluido con IntelliJ IDEA).
- **Postman** u otra herramienta para probar APIs (opcional).
- **PostgreSQL** (opcional, si decides usarlo como alternativa a MySQL).

---

## **Clonar el Proyecto y Configuración Inicial**

1. **Clona el repositorio desde GitHub:**

    - Abre **IntelliJ IDEA**.
    - Ve a `File > New > Project from Version Control`.
    - Pega la URL del repositorio y haz clic en `Clone`.
      ```bash
      https://github.com/tuusuario/tu-repositorio.git
      ```

2. **Abre el proyecto en IntelliJ IDEA:**

   Una vez clonado, IntelliJ detectará automáticamente que el proyecto utiliza **Maven**. Si no lo hace:
    - Ve a `File > Project Structure > Modules`.
    - Asegúrate de que el módulo del proyecto esté configurado como **Maven Module**.

3. **Actualiza las dependencias de Maven:**

    - Abre el archivo `pom.xml`.
    - Haz clic derecho en cualquier parte del archivo y selecciona **Reload Maven Project**.
    - Verifica que todas las dependencias se descarguen correctamente.

---

## **Configuración de la Base de Datos**

### **Opción 1: MySQL (Recomendada)**

1. **Crea la base de datos en MySQL Workbench:**

    - Abre **MySQL Workbench**.
    - Ejecuta el siguiente comando para crear la base de datos:
      ```sql
      CREATE DATABASE gestion_destinos;
      ```

2. **Configura las credenciales de MySQL en `application.properties`:**

   Abre el archivo `src/main/resources/application.properties` y configura las credenciales de conexión:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/gestion_destinos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña

   # Configuración adicional
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

    - Cambia `tu_usuario` y `tu_contraseña` por tus credenciales de MySQL.

3. **Verifica la conexión a la base de datos:**

    - Una vez configurado, ejecuta el proyecto en IntelliJ (`Run > Run 'GestionDestinosApplication'`).
    - Las tablas se crearán automáticamente en la base de datos `gestion_destinos`.

---

### **Opción 2: PostgreSQL (Alternativa para macOS)**

1. **Instala PostgreSQL:**

   Si estás en macOS, puedes instalar PostgreSQL utilizando Homebrew:
   ```bash
   brew install postgresql
   brew services start postgresql
   ```

2. **Crea la base de datos en PostgreSQL:**

    - Abre una terminal y ejecuta:
      ```bash
      psql postgres
      ```
    - Dentro de la consola de PostgreSQL, ejecuta los siguientes comandos:
      ```sql
      CREATE DATABASE gestion_destinos;
      CREATE USER tu_usuario WITH ENCRYPTED PASSWORD 'tu_contraseña';
      GRANT ALL PRIVILEGES ON DATABASE gestion_destinos TO tu_usuario;
      ```

3. **Agrega las dependencias de PostgreSQL en `pom.xml`:**

   Incluye la dependencia del controlador PostgreSQL:
   ```xml
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <version>42.6.0</version>
   </dependency>
   ```

4. **Configura las credenciales de PostgreSQL en `application.properties`:**

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_destinos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña

   # Configuración adicional
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.datasource.driver-class-name=org.postgresql.Driver
   ```

5. **Verifica la conexión a la base de datos:**

    - Ejecuta el proyecto en IntelliJ (`Run > Run 'GestionDestinosApplication'`).
    - Las tablas se crearán automáticamente en la base de datos `gestion_destinos`.

---

## **Puesta en Marcha**

1. **Ejecuta el Proyecto:**

    - Ve al menú superior de IntelliJ y selecciona `Run > Run 'GestionDestinosApplication'`.
    - Asegúrate de que no haya errores en la consola.
    - La aplicación estará disponible en:
      ```
      http://localhost:8080
      ```

2. **Prueba los Endpoints:**

   Utiliza una herramienta como **Postman** para interactuar con la API. A continuación, se detallan las pruebas de los endpoints disponibles.

---

## **Guía de Pruebas de Endpoints**

### **Usuarios**

#### **1. Crear un usuario**

- **Endpoint:** `POST /usuarios`
- **Body JSON:**
  ```json
  {
    "nombre": "Juan Pérez",
    "email": "juan.perez@example.com"
  }
  ```
- **Respuesta esperada:**
  ```json
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "email": "juan.perez@example.com",
    "destinosAsignados": []
  }
  ```

#### **2. Obtener todos los usuarios**

- **Endpoint:** `GET /usuarios`
- **Respuesta esperada:**
  ```json
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

- **Endpoint:** `PUT /usuarios/{usuarioId}/destinos/{destinoId}`
- **Ejemplo de URL:** `PUT /usuarios/1/destinos/2`
- **Respuesta esperada:**
  ```json
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

- **Endpoint:** `GET /usuarios/{usuarioId}/factura`
- **Ejemplo de URL:** `GET /usuarios/1/factura`
- **Resultado:**
    - Se descargará un archivo PDF con la factura del usuario que incluye:
        - Nombre del usuario.
        - Email.
        - Lista de destinos asignados con precios.
        - Total acumulado.

---

### **Destinos Turísticos**

#### **1. Crear un destino**

- **Endpoint:** `POST /destinos`
- **Body JSON:**
  ```json
  {
    "nombre": "Playa del Carmen",
    "precio": 1200.50
  }
  ```
- **Respuesta esperada:**
  ```json
  {
    "id": 2,
    "nombre": "Playa del Carmen",
    "precio": 1200.50
  }
  ```

#### **2. Obtener todos los destinos**

- **Endpoint:** `GET /destinos`
- **Respuesta esperada:**
  ```json
  [
    {
      "id": 2,
      "nombre": "Playa del Carmen",
      "precio": 1200.50
    }
  ]
  ```

---

## **Estructura del Proyecto**

```plaintext
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

---


## **Solución de Problemas Comunes**

### **1. No se crean las tablas en la base de datos**

#### **Causa: Configuración de Hibernate**

-   Asegúrate de que `spring.jpa.hibernate.ddl-auto=update` esté configurado en el archivo `application.properties`.

#### **Causa: Base de datos inexistente**

-   Verifica que la base de datos esté creada en MySQL Workbench o PostgreSQL.
-   Comando para MySQL:

    sql

    Copiar código

    `CREATE DATABASE gestion_destinos;`

-   Comando para PostgreSQL:

    sql

    Copiar código

    `CREATE DATABASE gestion_destinos;`


#### **Causa: Configuración incorrecta del datasource**

-   Asegúrate de que las credenciales sean correctas en `application.properties`:

    properties

    Copiar código

    `spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña`


#### **Causa: Controlador incorrecto**

-   Verifica que la dependencia del controlador correspondiente (MySQL o PostgreSQL) esté incluida en el archivo `pom.xml`.

----------

### **2. Error de conexión a la base de datos**

#### **Causa: Servicio MySQL o PostgreSQL no iniciado**

-   Para MySQL:

    bash

    Copiar código

    `sudo service mysql start`

-   Para PostgreSQL:

    bash

    Copiar código

    `brew services start postgresql`


#### **Causa: Puerto incorrecto**

-   Asegúrate de que el puerto configurado en `application.properties` coincida con el de tu base de datos:
    -   Para MySQL:

        properties

        Copiar código

        `spring.datasource.url=jdbc:mysql://localhost:3306/gestion_destinos`

    -   Para PostgreSQL:

        properties

        Copiar código

        `spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_destinos`


#### **Causa: Usuario o contraseña incorrectos**

-   Verifica que el usuario tenga permisos para acceder a la base de datos:
    -   Para MySQL:

        sql

        Copiar código

        `GRANT ALL PRIVILEGES ON gestion_destinos.* TO 'tu_usuario'@'localhost' IDENTIFIED BY 'tu_contraseña';`

    -   Para PostgreSQL:

        sql

        Copiar código

        `CREATE USER tu_usuario WITH ENCRYPTED PASSWORD 'tu_contraseña';
        GRANT ALL PRIVILEGES ON DATABASE gestion_destinos TO tu_usuario;`


----------

### **3. Dependencias de Maven no se descargan**

#### **Causa: Caché corrupto**

-   Limpia el caché de IntelliJ:
    -   Ve a `File > Invalidate Caches / Restart` y selecciona **Invalidate and Restart**.

#### **Causa: Repositorio Maven no accesible**

-   Verifica tu conexión a Internet.
-   Asegúrate de que el archivo `settings.xml` de Maven esté configurado correctamente.

#### **Causa: Dependencia faltante**

-   Agrega manualmente la dependencia en `pom.xml` y luego recarga Maven:

    bash

    Copiar código

    `mvn clean install`


----------

### **4. Error al generar facturas en PDF**

#### **Causa: Dependencia PDFBox faltante**

-   Agrega la siguiente dependencia al archivo `pom.xml`:

    xml

    Copiar código

    `<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>2.0.29</version>
    </dependency>`


#### **Causa: Error de escritura de archivos**

-   Asegúrate de que la aplicación tenga permisos de escritura en el sistema de archivos.
-   Verifica el directorio actual de ejecución para confirmar que el archivo PDF se está creando correctamente.

----------

### **5. Endpoint no encontrado (404 Not Found)**

#### **Causa: Ruta incorrecta**

-   Verifica que el endpoint sea el correcto, por ejemplo:
    -   `POST /usuarios`
    -   `GET /usuarios`
    -   `PUT /usuarios/{usuarioId}/destinos/{destinoId}`

#### **Causa: Controlador no detectado**

-   Asegúrate de que las clases del controlador estén anotadas con `@RestController`.
-   Verifica que estén en un paquete escaneado por la clase principal con `@SpringBootApplication`.

----------

### **6. Problema con relaciones bidireccionales en la base de datos**

#### **Causa: Duplicados en la tabla intermedia**

-   Limpia los registros duplicados manualmente:

    sql

    Copiar código

    `DELETE FROM usuario_destinos WHERE id NOT IN (
    SELECT MIN(id)
    FROM usuario_destinos
    GROUP BY usuario_id, destino_id
    );`


#### **Causa: Relaciones mal sincronizadas**

-   Asegúrate de usar métodos para manejar relaciones bidireccionales en los modelos:

    java

    Copiar código

    `public void agregarDestino(DestinoTuristico destino) {
    if (!this.destinosAsignados.contains(destino)) {
    this.destinosAsignados.add(destino);
    destino.getUsuarios().add(this);
    }
    }`

