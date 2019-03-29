# MATERA_TALENT_POOL
Matera Talent Pool

The Main class of this application is MateraTalentPoolApplication
You can simply run the method main of this class and search some employees with /employees URI.
For example: http://localhost:8080/employees
 
There is a database in memory called H2
You can access this database with /h2-console URI.
For example http://localhost:8080/h2-console
Using this configuration:
-
Saved Settings: Generic H2 (Embedded)
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password:
-
 
There is the file data.sql in src/main/resources directory with some inserts of employees.
It should load at the beginning of the application.
But you can copy the content of this file and run manually using /h2-console URI.

There is a Swagger Documentation in src/main/doc directory
You can access the file index.html
or copy the content of swagger.txt and past at Swagger Editor online (https://editor.swagger.io/)
