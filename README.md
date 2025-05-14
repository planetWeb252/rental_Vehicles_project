# Rental Vehicles Project
Rental vehicle management system with Spring Boot.  
It allows administrators to manage vehicles (cars, vans, etc.), and customers to reserve them.   
The system 
implements authentication with JWT and role-based access control.
---
## Enlaces de Interés
- 🌐 [Google Slides](https://docs.google.com/presentation/d/1CiaAXrMYRgR4OR7q_lKe1H3aQalHOOZDRRhaFnhWI6Yhttps://docs.google.com/presentation/d/1CiaAXrMYRgR4OR7q_lKe1H3aQalHOOZDRRhaFnhWI6Y/edit?usp=sharing/edit#slide=id.g358acabde9a_0_509)

- 🔗 [Capturas Postman](https://docs.google.com/presentation/d/1CiaAXrMYRgR4OR7q_lKe1H3aQalHOOZDRRhaFnhWI6Y/edit?usp=drive_link)
---
Folder directory
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

## ▶️  Run

1. Clone the repository
```` bash

git clone https://github.com/planetWeb252/rental_Vehicles_project.git

````
2. Configure the aplication.properties, the application needs a MySQL database.
3. Run the Project with Maven
```` bash

./mvnw spring-boot:run

````
4. Inicia Postman
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
| Método | Ruta                                                                                                                                                                                                            
| ------ |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
| POST   | `/api/public/login`<br/>`/api/public/customer`<br/> `/api/public/employee/register/admin` <br/> `/api/public/employee/register/employeeOthers ` <br/> ` /api/public/vehicle`                                    
| GET    | `/api/public/customer/allCustomer` <br/> `/api/public/employee/register/allEmployee`<br/> `/api/private/rental/all`<br/> `/api/private/rental/request/{licensePlate}`     <br/>`/api/public/vehicle/allVehicles`
| PUT    | `/api/public/approve/{rentalId}`<br/>`/api/public/vehicle/updateVehicle/{licensePlate}`                                                                                                                                                            
| DELETE | `/api/public/vehicle/deleteVehicle/{licensePlate}`                                                                                                                                                                                             

## ✅ Pruebas
El proyecto incluye pruebas unitarias y de integración con JUnit y MockMvc. Para ejecutarlas:
``` bash

./mvnw test
```

## 👤 Autor
- [DevJerryX](https://github.com/planetWeb252)
