# File Upload/Download Project with Spring Boot and Angular

This project is a simple web application that allows users to upload and download files using a web interface. The application is built using Spring Boot on the backend and Angular on the frontend.

## Prerequisites

Before you start, you should have the following installed on your computer:

- Java JDK 8 or higher
- Node.js and npm
- Angular CLI
- MySQL or another relational database

## Getting started

1. Clone the repository to your local machine
2. Create a MySQL database 
3. Open `src/main/resources/application.yml` and configure the database connection settings
4. Build and run the Spring Boot application using your favorite IDE or by running the command `mvn spring-boot:run` from the terminal or your run otpion in your IDE
5. Open a new terminal window and navigate to the `client` directory
6. Run the command `npm install` to install the required dependencies
7. Run the command `ng serve` to start the Angular development server
8. Open a web browser and navigate to `http://localhost:4200` to access the application

## Usage

To upload a file, click the "Choose File" button and select a file from your computer. Once you have selected a file, click the "Upload" button to upload the file to the server.

To download a file, click the "Download" button next to the file you wish to download. The file will be downloaded to your computer.
