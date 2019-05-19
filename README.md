# spring-hibernate

## Introduction 
1. This a sample project for Spring + Hibernate/JPA + MySQL

## Prequisite 
1. Installed Java JDK 1.8.x
2. Installed MySQL 8.x.x and create a local database connection.
3. Installed Apache Maven 3.6.x
4. (Optional) Installed any preferred IDE. Recommended to use Eclipse 2019-03.
5. Installed Apache Lombok 1.x.x in IDE.

## Getting Started 

Setup DB data
1. Execute `sample-data.sql` in your local db.
2. Modify `persistence-mysql.properties` and point to your local db.

Execute application
1. Run `MainApplication.java`

## Troubleshoot

1. Make sure MySQL Connector version match your local MySQL DB version. Let say your DB is 5.x.x, then change then pom.xml to 5.x.x.
