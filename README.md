# JAVA_PARCIAL_NAVEDA
Sistema de Gestión de Vehículos

# Descripción General  
Este es un proyecto hecho en Java para gestionar vehículos, tanto autos como motos, con persistencia en base de datos MySQL mediante JDBC.  
Permite registrar, buscar, listar y eliminar autos y motos, asegurando que no se repita la patente entre ambos tipos.

La aplicación funciona por consola, con una interfaz interactiva para el usuario, y está estructurada en múltiples clases para mantener una buena organización. También se manejan excepciones y se valida la existencia de patentes antes de insertar.


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
ES IMPORTANTE QUE TENGA UNA BASE DE DATOS LLAMADA: BD_Parcial_NAVEDA
Y QUE SI NO TIENE CONTRASEÑA EN LA BASE DE DATOS CAMBIE ESTA LINEA EN DataBaseManager LINEA 14:  private static final String PASSWORD = "1234"; / POR ESTA:  private static final String PASSWORD = "";
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---

# Funcionalidades Principales

Gestión de Autos  
- Alta de autos con marca, modelo, puertas y heredando de Vehiculo: peso, patente y capPasajeros  
- Eliminación de autos por id 
- Listado completo de autos  
- Búsqueda por patente  
- Eliminacion y actualizacion (solo disponible en modo administrador)

Gestión de Motos  
- Alta de motos con marca, modelo, cilindrada y heredando de Vehiculo: peso, patente y capPasajeros 
- Eliminación de motos por id 
- Listado completo de motos  
- Búsqueda por patente  
- Eliminacion y actualizacion (solo disponible en modo administrador)

Validación de Patentes  
- No se permite registrar una misma patente en autos y motos  
- La lógica de validación está centralizada en `DataBaseManager`

---

# Tecnologías Utilizadas  

Java     | Lenguaje principal del proyecto 
MySQL    | Base de datos relacional para persistencia de datos 
JDBC     | Conexión y operaciones con la base de datos 
Consola  | Interfaz de texto para el usuario 

---

# Organización del Proyecto

El proyecto está dividido en clases específicas para separar responsabilidades:

- CARPETA Main: Punto de entrada de la aplicación, muestra el menú y permite navegar entre opciones. Encapsula toda la lógica de interacción con el usuario (pedir datos, mostrar listas, etc.).
- CARPETA Dao: Contiene las clases AutoDAO y MotoDAO, las cuales tienen todos los metodos de interaccion con objetos hacia la base de datos  
- CARPETA Util: Tiene la clase DataBaseManager: Gestiona la conexión con la base de datos y creación de tablas 
- CARPETA Modelo: Contiene las clases AUTO, MONO Y VEHICULO: tienen todos los constructores y creacion de cada objeto con sus respectivos atributos 
---


# Requisitos Previos
- Tener instalado Java JDK 17 o superior  
- Tener una base de datos MySQL en funcionamiento  
- Tener un IDE (IntelliJ IDEA, NetBeans, Eclipse, etc.)  

ependencias Necesarias  
- Conector JDBC para MySQL: implementation 'mysql:mysql-connector-java:8.0.33' (colocar esa linea en la carpeta build.gradle, en la parte de dependencies{ }) 
- Archivo de configuración de conexión (usuario, contraseña, URL de la base)

---

# Sobre la Base de Datos

- Tablas creadas automáticamente al ejecutar el programa:
  - `Auto`: contiene `Id` y `Nombre' que luego se le hace un INSERT INTO Auto (PATENTE, PESO, CAPACIDAD_PASAJEROS, MARCA, MODELO, PUERTAS) VALUES (?, ?, ?, ?, ?, ?)";
  - `Moto`: contiene `Id`y `Nombre` que luego se le hace un INSERT INTO Moto (PATENTE, PESO, CAPACIDAD_PASAJEROS, MARCA, MODELO, CILINDRADA) VALUES (?, ?, ?, ?, ?, ?)";
- La conexión está configurada en `DataBaseManager` y requiere modificar los datos de acceso si usás otra configuración local
---
# AUTOR
Nombre: Tomas Mastropietro
Materia: Programación II - UTN FRM  
Año:2025

# RUTA A SEGUIR PARA ABRIR LAS CARPETAS DEL PROYECTO: src\main\java\org\example
