# Real Estate Buying & Renting System

A web application for listing, buying, and renting real estate properties.  
Built with **Java Spring Boot** (REST + MVC), **Spring Security (JWT + RBAC)**, **JPA/Hibernate**, **MySQL**, and **JSP/Bootstrap**.

## Features
- Authentication & Authorization: **JWT** login, **Role-based access control (Admin/Manager/Staff/User)**
- Property listing: create/update/delete properties with images, price, location, status
- Advanced search & filter (e.g., 16 filters): location, price range, area, type, status, ...
- Pagination & sorting for large datasets
- Transaction workflow: customer requests → staff approves/handles → status tracking
- Admin dashboard: manage users, posts, transactions
- Async events with Kafka: notifications / audit logs / background tasks

## Tech Stack
- Backend: Java, Spring Boot, Spring MVC, Spring Security (JWT), JPA/Hibernate
- Database: MySQL
- Frontend: JSP, Bootstrap, jQuery/Ajax
- Tools: Git, Maven/Gradle
- Messaging: Kafka

## Screenshots
- Home / Search page
  <img width="1894" height="904" alt="image" src="https://github.com/user-attachments/assets/f8265df3-d4b2-4484-bc9b-67e0b516f27a" />
- Admin dashboard
  <img width="1890" height="901" alt="image" src="https://github.com/user-attachments/assets/52f07431-8eeb-4f1b-95c2-2bb053634622" />
  <img width="1914" height="905" alt="image" src="https://github.com/user-attachments/assets/93396bb7-3b47-413c-8f06-f7985a037b04" />
  <img width="1906" height="910" alt="image" src="https://github.com/user-attachments/assets/7815e7a6-c114-4203-9872-f481e963dc7d" />
  <img width="1913" height="893" alt="image" src="https://github.com/user-attachments/assets/891b3891-635c-4686-a2db-fd87c7b0ef90" />
## Architecture
- 3-layer architecture: **Controller → Service → Repository**
- Security: JWT filter, RBAC via roles/permissions
- Modules:
  - Auth / Users
  - Properties
  - Transactions
  - Admin management

## Getting Started

### Prerequisites
- Java 17+ (or your version)
- Maven 3.8+ (or Gradle)
- MySQL 8+
- Kafka
