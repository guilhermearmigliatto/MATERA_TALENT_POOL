package com.matera.talent.pool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* To run this application in Java IDE you first need to build the project with maven (https://maven.apache.org/)
* You can use the command:
* "mvn clean install"
* or if you are using eclipse IDE:
* "mvn clean install eclipse:eclipse"
* 
* Next, import the project in one Java IDE and run the main method in the main class.
* The Main class of this application is MateraTalentPoolApplication
* You need to run the method main of this class and search for some employees with /employees URI in a normal browser.
* For example: http://localhost:8080/employees
* 
* You can also run the jar file in the root of this project
* Install Java JDK 1.8 or higher in you computer and use the command:
* "java -jar talent-pool-0.0.1-SNAPSHOT.jar"
* You can test it searching for some employees with /employees URI in a normal browser.
* For example: http://localhost:8080/employees
* 
* There is a database in memory called H2
* You can access this database with /h2-console URI in a normal browser.
* For example http://localhost:8080/h2-console
* Using this configuration:
* -
* Saved Settings: Generic H2 (Embedded)
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:testdb
* User Name: sa
* Password:
* -
* 
* There is the file data.sql in src/main/resources directory with some inserts of employees.
* It should load at the beginning of the application.
* But you can copy the content of this file and run manually using /h2-console URI to populate the database.
* 
* A Postman file called postman_collection.json is committed in the directory src/main/doc
* This file contains examples of URI's and Json objects to use in this application.
* You can see examples of methods GET, PUT, POST and DELETE.
* You need to download the Postman application in (https://www.getpostman.com) and import this file to see and run this examples.
* 
* There is a Swagger Documentation in src/main/doc directory
* This documentation has all the URI's of this API along with its parameters and responses.
* You can access the file index.html
* or copy the content of swagger.txt and past at Swagger Editor online (https://editor.swagger.io/)
*/
@SpringBootApplication
public class MateraTalentPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MateraTalentPoolApplication.class, args);
	}
}
