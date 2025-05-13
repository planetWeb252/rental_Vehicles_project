# Rental Vehicles Project
Sistema de gestión de vehiculos de alquiler con Spring Boot.  
Permite a los administradores gestionar vehículos (coches, furgonetas, etc.), y a los clientes reservarlos.  El 
sistema implementa autenticación con JWT y control de acceso basado en roles.


---
Directorio de carpetas   
---
```` text
src/
├── main/
│ ├── java/com/planetweb/rentalvehicles/
│ │ ├── config/
│ │ ├── controller.routes/
│ │ ├── DTO/
│ │ ├── enums/
│ │ ├── exceptions/
│ │ ├── models/
│ │ ├── repository/
│ │ └── service/
│ └── resources/
│ ├── application.properties
└── test/
└── README.md

````
---

## Como ejecutar el Proyecto

1. Clona el repositorio
```` bash

git clone https://github.com/planetWeb252/rental_Vehicles_project.git

````
2. Confugura el aplication.properties
3. Ejecuta el Proyecto con Maven
```` bash

./mvnw spring-boot:run

````
## 🔐 Seguridad y autenticación

El proyecto tiene rutas en controllers.routes  
- Login: `/api/public/login` 
- Registro de Customer: `api/public/customer`
- Registro de Employee Admin: `/api/public/employee/register/admin`
- Registro de Employee: `/api/public/employee/register/employeeOthers`
- Los Token JWT deben incluirse en el header `Authorization: Bearer <token>`

Roles
- `Admin Employee`: Puede gestionar vehiculos, aprobar reservas, ve todas las reservas, crear nuevos Employee con 
  diferentes roles.
- `Customer`: Pueder ver y reservar vehiculos

## 📦 Endpoints principales
| Método | Ruta                 | Descripción                | Roles permitidos |
| ------ | -------------------- | -------------------------- | ---------------- |
| POST   | `/api/vehicles`      | Crear un nuevo vehículo    | ADMIN            |
| GET    | `/api/vehicles`      | Listar todos los vehículos | ADMIN, CLIENT    |
| POST   | `/api/reservations`  | Crear reserva de vehículo  | CLIENT           |
| PUT    | `/api/vehicles/{id}` | Editar vehículo            | ADMIN            |
| DELETE | `/api/vehicles/{id}` | Eliminar vehículo          | ADMIN            |
