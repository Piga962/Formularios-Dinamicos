# Formularios Dinámicos con Patrón Abstract Method

## Descripción de la Actividad

En esta actividad, se implementará el patrón de diseño Abstract Method para actualizar dinámicamente un formulario en el frontend desarrollado en React, de acuerdo con el tipo de usuario (Admin o Guest). El backend estará construido con Spring Boot, y los datos de configuración de los formularios se obtendrán desde una base de datos en Firebase.

El objetivo es que, al detectar el tipo de usuario, el sistema cargue y muestre automáticamente un formulario con los campos específicos correspondientes:

- Para usuarios **Admin**: mostrar 3 campos específicos.
- Para usuarios **Guest**: mostrar 3 campos distintos.

El patrón Abstract Method será utilizado en el backend para definir una interfaz genérica para la generación de formularios, permitiendo que las subclases concreten los campos requeridos según el tipo de usuario. Esto permitirá mantener una arquitectura flexible y extensible para futuros tipos de usuarios.

### Tareas específicas:

1. Crear una interfaz en el backend que defina los métodos para obtener los campos del formulario.
2. Implementar subclases concretas para Admin y Guest que proporcionen sus respectivos campos.
3. Conectar con Firebase para recuperar la configuración dinámica de los formularios.
4. Exponer un endpoint REST en Spring Boot que retorne los campos del formulario según el tipo de usuario.
5. En React, detectar el tipo de usuario al iniciar sesión y consumir el endpoint para construir el formulario dinámicamente.

Esta actividad fortalece el entendimiento del patrón de diseño Abstract Method, el desarrollo de formularios dinámicos basados en roles de usuario y la integración entre frontend y backend con servicios en la nube (Firebase).

## Estructura del Proyecto

- `/backend`: Backend Spring Boot con implementación del patrón Abstract Method
- `/frontend`: Frontend React con renderizado dinámico de formularios

## Configuración de Firebase

1. Crear un proyecto en Firebase en https://console.firebase.google.com/
2. Configurar Firestore Database
3. Reemplazar el archivo `firebase-service-account.json` con tu clave de cuenta de servicio de Firebase
4. Utilizar la siguiente estructura de base de datos en Firestore:

```
Colección: forms
   Documento: admin
      formTitle: "Admin Management Form"
      formDescription: "Form for administrative tasks and project management"
      fields: [
         {
            id: "adminField1",
            label: "Project Name",
            type: "text",
            required: true,
            placeholder: "Enter project name"
         },
         {
            id: "adminField2",
            label: "Budget Allocation",
            type: "number",
            required: true,
            placeholder: "Enter budget"
         },
         {
            id: "adminField3",
            label: "Access Level",
            type: "select",
            required: true,
            options: ["High", "Medium", "Low"]
         }
      ]

   Documento: guest
      formTitle: "Guest Registration Form"
      formDescription: "Please provide your information to proceed as a guest"
      fields: [
         {
            id: "guestField1",
            label: "Full Name",
            type: "text",
            required: true,
            placeholder: "Enter your full name"
         },
         {
            id: "guestField2",
            label: "Email",
            type: "email",
            required: true,
            placeholder: "Enter your email address"
         },
         {
            id: "guestField3",
            label: "Reason for Visit",
            type: "textarea",
            required: true,
            placeholder: "Please tell us why you're here today"
         }
      ]
```

## Instrucciones para Ejecutar el Proyecto

### Ejecutar el Backend

1. Navegar al directorio del backend:
   ```
   cd backend
   ```

2. Ejecutar la aplicación Spring Boot:
   ```
   ./mvnw spring-boot:run
   ```
   O si tienes Maven instalado:
   ```
   mvn spring-boot:run
   ```

### Ejecutar el Frontend

1. Navegar al directorio del frontend:
   ```
   cd frontend
   ```

2. Instalar dependencias:
   ```
   npm install
   ```

3. Iniciar el servidor de desarrollo:
   ```
   npm start
   ```

4. Abrir [http://localhost:3000](http://localhost:3000) en tu navegador.

