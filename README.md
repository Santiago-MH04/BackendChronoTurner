![Chrono-Turner logo here](path/to/image.png)

# Chrono-Turner Backend
Turning chaos into clarity. A new era of personal organisation is about to begin.

## Features and advantages
- Elevate your productivity
- Manage your tasks with elegance and efficiency
- We make productivity feel effortless and even enjoyable

## How to use

Instructions for installing and setting up the project:

[//]: # (1. Clone, download or fork, on the first hand, the front-end repository)
[//]: # (    ```bash)
[//]: # (   git clone https://github.com/PablolzDev/Chrono-Turner-FrontEnd.git)
   
[//]: # (2. Clone, download or fork on the second hand, the backend repository)
Clone, download or fork, the backend repository

   ```bash
   git clone https://github.com/Santiago-MH04/BackendChronoTurner.git
   ```

Once you have the repository at hand, you can also take a look at the development-environment documentation for the endpoints once you have run the application, and then either logged in, or signed up, since it also implements security.
You can take a look at the endpoints documentation by clicking [here](http://18.191.247.216/swagger-ui.html) and submitting the token. Otherwise, Swagger will not show you the information of the endpoints.

In the same way, and before you run the application, when you open the .env file, located on resources folder, you need to set the new environment variables in order to get a proper connection to a database, so it will properly work.

## Content

1. What does the backend do?

The backend comes together with the layers that separate responsibilities, such as respositories, services, controllers, entity classes, etc. As the reader can notice, most of the responsibilities were assigned to a single Git branch. However, as developers, we have made sure that, in the end, every branch shares the same information, so we can avoid errors.

In order for Chrono-Turner to work, we have also configured some environmental variables, by using the dotenv library, available in [maven repository](https://mvnrepository.com/) so, if anyone is testing the application, we could run it in our local environments, and then we used a single remote database before making the final delivery. The file used for that end is called .env.

In summary, each one of the following directories represent a layer of our project:

* **controllers**: contains a file for Entity class, with the endpoints associated for that Entity
* **dto**: contains the dto classes we judged necessary for the project to run, with no further complications
* **email**: contains a service that sends and email to every new user, so they can validate their accounts
* **encryption**: contains all the files in charge of creating and validating the token (Spring Security)
* **entities**: contains all the files with Entity classes, used under the JPA paradigm
* **exceptions**: contains the files with some of the personalised error classes, so the HTTP response is not so long
* **interceptors**: contains the files with some cross-cutting loggings that will serve us, as developers, to track the use of the application, such as a notice when someone signs up, or deletes their accounts
* **repositories**: contains the files of the interfaces that extend JpaRepository<Entity, ID>, so the methods of it are under control
* **security**: contains the filter files that authorise the application to run whenever the authentication is required
* **services**: contains the abstractions and implementations of the service layer, so we can decide which one to inject, in case there is more than one implementation of the repositories, e.g. when there's different databases, from different database engines
* **utils**: contains some enum classes that are used as constants for fields in the entity classes
* **Application and configuration classes**: contain the Spring Boot link that allow our project to execute


