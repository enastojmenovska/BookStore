# BookStore
This project is stuctured in two parts: Spring Boot API as backend and React app as frontend. 

The Spring Boot API serves for library management system, that offers librarians to oversee the library's inventory, including adding new books, removing those in poor condition, and updating book details. The application also allows for the management of author and country records in a similar manner. The project is structured using a layered architecture, consisting of rest controllers, services and repositories. The controllers manage the incoming HTTP requests and forward them to the appropriate services. The services contain the business logic and manage interactions with the repositories. Finally the repositories serve as the interface between the application and the database.

The React application interacts with the backend API provided by the Spring Boot library management system. It provides endpoint for managing books and visualy represents the book categories and authors.

The React application does not include the node_modules directory or any of the dependencies in the provided package due to space considerations. To set up the project, you will need to run 'npm install' to download and install all the necessary dependencies.
