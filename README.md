# Student Management System (JPA + Hibernate + Spring Data JPA)

This project matches **Module 11 — Building Your First JPA Project** from the guide:
a Spring Boot REST API backed by MySQL, using Spring Data JPA / Hibernate.

## Folder structure

```
StudentManagementSystem/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/example/studentmanagement/
    │   ├── StudentManagementSystemApplication.java   (main class)
    │   ├── model/Student.java                        (@Entity)
    │   ├── repository/StudentRepository.java          (JpaRepository)
    │   ├── service/StudentService.java                 (business logic)
    │   └── controller/StudentController.java            (REST endpoints)
    └── resources/
        └── application.properties                     (DB config)
```

## 1. Import into Eclipse

1. Open Eclipse.
2. `File -> Import... -> Maven -> Existing Maven Projects`.
3. Click **Next**, then **Browse**, and select the `StudentManagementSystem` folder
   (the one containing `pom.xml`).
4. Click **Finish**. Eclipse will download dependencies from Maven Central
   (this can take a minute the first time).

   > If Eclipse doesn't have the "Maven" import option, install the
   > **m2e (Maven Integration for Eclipse)** plugin from
   > `Help -> Eclipse Marketplace`, or use **Spring Tool Suite (STS)**,
   > which has this built in.

## 2. Create the database

Before running, create an empty database in MySQL:

```sql
CREATE DATABASE student_db;
```

Hibernate will create/update the `students` table automatically because
`spring.jpa.hibernate.ddl-auto=update` is set in `application.properties`.

## 3. Set your DB credentials

Edit `src/main/resources/application.properties` and replace
`your_password` with your actual MySQL root password (or a dedicated user):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=your_password
```

## 4. Run the project

In Eclipse:
- Right-click `StudentManagementSystemApplication.java`
- `Run As -> Java Application` (or `Run As -> Spring Boot App` if using STS)

The server starts on **http://localhost:8080**.

## 5. Test with Postman (Module 12)

| Action        | Method | URL                     | Body |
|---------------|--------|-------------------------|------|
| Create student| POST   | `/students`             | `{"name":"Ritik","email":"ritik@gmail.com","age":23,"course":"Java"}` |
| Get all       | GET    | `/students`             | — |
| Get one       | GET    | `/students/{id}`        | — |
| Update        | PUT    | `/students/{id}`        | same JSON shape as create |
| Delete        | DELETE | `/students/{id}`        | — |

## Notes

- `ddl-auto=update` is convenient for learning but not recommended for production —
  see the guide's note in Module 11.
- The `PUT /students/{id}` update endpoint isn't in the original PDF module but is
  added here so the CRUD set (Module 10) is complete and testable end-to-end.
- Next steps from the guide you can layer on top of this base project:
  relationships (Module 13), fetch strategies (Module 14), cascade types (Module 15),
  query methods (Module 16), `@Query`/JPQL (Module 17), `@Transactional` (Module 18),
  and pagination/sorting (Module 19).
