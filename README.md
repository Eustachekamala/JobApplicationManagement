# Job Application Management System

![Job Application Management](src/doc/JobApplicationManagement.png)

## 🗄️ Database Diagram
### Table Structure:
* Applicants:
  * One-to-One with Resume
  * One-to-Many with Applications
  * Many-to-Many with Jobs (via Applications)
* Resume:
  * One-to-One with Applicants
  * Stores file URL or PDF blob
* Applications:
  * Many-to-One with Applicants
  * Many-to-One with Jobs
* Jobs:
  * One-to-Many with Applications
  * Many-to-Many with Applicants (via Applications)
* Applicant_Jobs (Join Table):
  * Many-to-Many relationship between Applicants and Jobs

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-24.0.6-blue?logo=docker)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17.0-blue?logo=postgresql)](https://www.postgresql.org/)
[![OpenAPI](https://img.shields.io/badge/OpenAPI-3.1.0-red?logo=openapiinitiative)](https://www.openapis.org/)

A Spring Boot application for managing job applicants, jobs, and applications.  
Built with **Java 21, Spring Boot, JPA (Hibernate), PostgreSQL**, and packaged with **Docker** and **Jenkins**.

---

## 🚀 Features
- Manage Applicants (create, update, delete, list all)
- Manage Jobs (create, view by ID, list all, assign to applicants)
- Manage Applications (create links to applicants)
- REST API with OpenAPI/Swagger documentation

---

## 🐳 Running with Docker

### 1. Pull the image
```bash
docker pull eustachekamala/job-application-management-system:latest
```
### 2. Run the container
```bash
docker run -d -p 8080:8080 eustachekamala/job-application-management-system:latest
```
### 3. Access the application
- API Documentation: [https://job-application-management-system-latest-qvm4.onrender.com/swagger-ui/index.html](https://job-application-management-system-latest-qvm4.onrender.com/swagger-ui/index.html)
- OpenAPI Spec: [https://job-application-management-system-latest-qvm4.onrender.com/v3/api-docs](https://job-application-management-system-latest-qvm4.onrender.com/v3/api-docs)
