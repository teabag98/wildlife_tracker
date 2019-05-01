# Wildlife Tracker
## author
[Eric king'oo](https://www.github.com/teabag98)

## Description
WIldlife tracker application enables ranger to record sightations  of animals , Endagered and non-endagered categories.

### Requirements
* Java
* Gradle
* text-editor(intelli j)
## Setup and Installation requirements
* Go to the projects [repository](https://github.com/teabag98/wildlifeTracker)
* Clone the project
```
git clone https://github.com/teabag98/hairSalon.git
```

* sdk install gradle 4.8.1
```

* sdk install java
```
* Open the directory in terminal
* move to main
```
cd build/classes/java/main
```
* Run the following command to execute the Terminal-java application
```
java App
```
* In PSQL:
```
CREATE DATABASE wildlife;
CREATE TABLE sightings (id serial PRIMARY KEY, rangername varchar,endagered varchar,location varchar,name varchar,health varchar,age varchar,categoryid int);
CREATE TABLE animals (id serial PRIMARY KEY, name varchar);
```

## contact
[email me](eric.kingoo2014@gmail.com)
## Technologies used:
* Java
* spark
*postgresql


## Licence
*MIT licence