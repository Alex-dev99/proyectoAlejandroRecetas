# proyectoAlejandroRecetas

---

## 📋 Descripción del Proyecto

Mi proyecto consiste en una API REST desarrollada en Spring Boot para la gestión completa de recetas culinarias. El sistema permite crear, consultar, actualizar y eliminar recetas, ingredientes y categorías, además de gestionar las relaciones entre ellos de manera eficiente.

## 🏗️ Arquitectura del Proyecto
Tecnologías Utilizadas

Java 17+ 

Spring Boot 3.x 

Spring Data JPA 

Hibernate 

Lombok 

Base de datos relacional 


## Estructura del Proyecto
src/main/java/com/salesianostriana/dam/proyectoAlejandroRecetas/

── controller/          # Controladores REST

── model/              # Entidades JPA

── repository/         # Interfaces de repositorio

── service/           # Lógica de negocio

── dto/               # Objetos de transferencia de datos

── exception/         # Excepciones personalizadas



## 📊 Modelo de Datos

### Entidades Principales

---

🍳 Receta
id (Long) - Identificador único

nombre (String) - Nombre de la receta (único)

tiempoPreparacionMin (Integer) - Tiempo en minutos

dificultad (Enum) - FACIL, MEDIA, DIFICIL

categoria (Categoria) - Categoría asociada

ingredientes (List<RecetaIngrediente>) - Ingredientes con cantidades

---

🥕 Ingrediente

id (Long) - Identificador único

nombre (String) - Nombre del ingrediente (único)

recetas (List<RecetaIngrediente>) - Recetas que lo contienen

---

📂 Categoria

id (Long) - Identificador único

nombre (String) - Nombre de la categoría (único)

descripcion (String) - Descripción de la categoría

---

🔗 RecetaIngrediente (Tabla intermedia)

id (Long) - Identificador único

receta (Receta) - Receta asociada

ingrediente (Ingrediente) - Ingrediente asociado

cantidad (String) - Cantidad requerida (ej: "2 tazas", "200g")

---


## 🚀 API Endpoints


### 📚 Recetas

Método	Endpoint	Descripción
GET	/api/v1/recetas	Obtener todas las recetas
GET	/api/v1/recetas/{id}	Obtener receta por ID con ingredientes
POST	/api/v1/recetas	Crear nueva receta
PUT	/api/v1/recetas/{id}	Actualizar receta existente
DELETE	/api/v1/recetas/{id}	Eliminar receta
POST	/api/v1/recetas/{recetaId}/ingredientes	Añadir ingrediente a receta
DELETE	/api/v1/recetas/{recetaId}/ingredientes/{ingredienteId}	Eliminar ingrediente de receta



### 🥕 Ingredientes
Método	Endpoint	Descripción
GET	/api/v1/ingredientes	Obtener todos los ingredientes
GET	/api/v1/ingredientes/{id}	Obtener ingrediente por ID
POST	/api/v1/ingredientes	Crear nuevo ingrediente
PUT	/api/v1/ingredientes/{id}	Actualizar ingrediente existente
DELETE	/api/v1/ingredientes/{id}	Eliminar ingrediente



### 📂 Categorías
Método	Endpoint	Descripción
GET	/api/v1/categorias	Obtener todas las categorías
GET	/api/v1/categorias/{id}	Obtener categoría por ID
POST	/api/v1/categorias	Crear nueva categoría
PUT	/api/v1/categorias/{id}	Actualizar categoría existente
DELETE	/api/v1/categorias/{id}	Eliminar categoría



## ⚠️ Manejo de Errores

El sistema implementa un manejo centralizado de excepciones con los siguientes tipos:

EntidadNoEncontradaException (404) - Recurso no encontrado

NombreDuplicadoException (409) - Nombre ya existe en el sistema

IngredienteYaAnadidoException (409) - Ingrediente ya añadido a la receta

TiempoInvalidoException (400) - Tiempo de preparación inválido


## 🔧 Características Técnicas

### Validaciones Implementadas
Nombres únicos para recetas, ingredientes y categorías

Tiempo de preparación mayor a 0 minutos

Prevención de ingredientes duplicados en recetas

---

### Optimizaciones
Transacciones en operaciones críticas

Separación DTO/Entity para control de datos expuestos

Consultas personalizadas en repositorios

---

### Patrones de Diseño
Controller-Service-Repository (Separación de responsabilidades)

DTO (Data Transfer Object)

Repository 

Global Exception Handling
