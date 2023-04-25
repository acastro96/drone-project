## Overview
The project is designed to provide a RESTful API for drones. It is built using Spring Boot, which provides an easy-to-use framework for developing Java applications, and follows a layered architecture for improved separation of concerns and maintainability.

## Architecture
The project follows a layered architecture, which separates different components of the application into distinct layers, each with its own responsibilities:

Controller Layer: This layer is responsible for handling incoming HTTP requests and sending responses. It consists of controllers that handle incoming RESTful API requests and manage the interaction with the service layer.

Service Layer: This layer contains the business logic of the application. It processes requests from the presentation layer, performs necessary operations, and communicates with the data access layer to retrieve or update data.

Data Access (Repository) Layer: This layer is responsible for interacting with the underlying data source, such as a database or an external API. It handles operations related to data retrieval, storage, and modification.

Utilities: This layer may include utility classes, such as exception handlers, security filters, or configuration files, that are used across different layers of the application.

## Technologies Used
The project utilizes the following technologies:

Spring Boot: A powerful Java framework for building web applications and APIs.
Spring Data JPA: A data access framework that provides abstraction over various data sources.
H2: A popular in-momery data base.
Maven: A build automation tool for Java projects.

## Getting Started
To get started with the project, follow these steps:

Clone the repository to your local machine.
Install and configure the necessary dependencies (Java, Maven, etc.).
Build and run the application using Maven or your preferred IDE.
Access the RESTful API using the provided endpoints and test with a tool such as Swagger or Postman.

## API Documentation
All the requests for this API are in a postman collection that was shared previously.

## Contributing
Contributions to the project are welcome! If you would like to contribute, please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix.
Make your changes and test them thoroughly.
Submit a pull request describing your changes and their purpose.

## Contact Information
For any questions or inquiries, please contact acas9616@gmail.com
