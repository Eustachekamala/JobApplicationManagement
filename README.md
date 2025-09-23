# Job Application Management System

A Spring Boot application for managing job applicants, jobs, and applications.  
Built with **Java 21, Spring Boot, JPA (Hibernate), PostgreSQL**, and packaged with **Docker**.

---

## 🚀 Features
- Manage Applicants (create, update, delete, list all)
- Manage Jobs (create, view by ID, list all, assign to applicants)
- Manage Applications (create linked to applicants)
- REST API with OpenAPI/Swagger documentation

---

## 🐳 Running with Docker

### 1. Pull the image
```bash
docker pull eustache21/job-application-management-system:latest
```
### 2. Run the container
```bash
docker run -d -p 8080:8080 eustache21/job-application-management-system:latest
```
### 3. Access the application
- API Documentation: [https://job-application-management-system-latest-qvm4.onrender.com/swagger-ui/index.html](https://job-application-management-system-latest-qvm4.onrender.com/swagger-ui/index.html)
- OpenAPI Spec: [https://job-application-management-system-latest-qvm4.onrender.com/v3/api-docs](https://job-application-management-system-latest-qvm4.onrender.com/v3/api-docs)