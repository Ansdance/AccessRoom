

## Plain REST API CRUD with Spring Boot 2 and PostgreSQL.

[![Build Status](https://travis-ci.org/OKaluzny/spring-boot-rest-api-postgresql.svg?branch=master)](https://travis-ci.org/OKaluzny/spring-boot-rest-api-postgresql)

### Technology stack:

* Spring Boot 2;
* Spring MVC;
* Spring Web;
* PostgreSQL database;
* Hibernate;
* Lombok;
* Spring Security (as basic authentication);
* JDBC;
* Thymeleaf;
* Bootstrap;
* bcrypt;



# Deployment
In order to deploy this projects, you need to perform some steps:
* About 15 minutes: 
* Intellij IDEA
* <a href="https://www.oracle.com/java/technologies/javase-jdk11-downloads.html">JDK 11</a>
* <a href="https://maven.apache.org/download.cgi">Maven 3.0+</a>
* <a href="https://www.postgresql.org/download/">PostgreSQL</a>
     
# Installation

Download project

## Install PostgreSql

![postgresql](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/postgresql-logo.png)


After that, you should create database (two ways, first from the PgAdmin -graphical interface, with just a right click of mouse ->Databases -> Create -> database/
or write command in database console: create database Access(in my case name of db is ACCESSTASK)), give a different name (exp: Access )

![database](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/posgresql.png)



Don't forget to add own login/password of DB to config file : /AccessRoom/blob/master/src/main/java/com/ansar/trainingcourse/Application.java


## Create User

Press Sign up


![signup](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/signup.png)


then create User


![user](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/user.png)


## Set Role

set role -> "ADMIN", For aministrator in PostgreSQL table -> user_authorities -> authorities


![role](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/role.png)



ReRun application. 


## Create Rooms


Enter by User which have role ADMIN, and create Rooms


![rooms](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/rooms.png)


## Set Access room for Users


You should enter -> All Users -> Edit


![access](https://github.com/Ansdance/AccessRoom/blob/master/src/main/resources/static/img/access.png)






## About Task

* System should be developed using SpringBoot;
* Json to serialize entities;
* Use PosgreSQL as a database;

## Contacts

 Follow me or Connect me via <a href="https://www.linkedin.com/in/ansar-dauletbayev-8b6630a3">Linkedin<a/>



