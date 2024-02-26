# Prueba Backend SDG Group
Actividad realizada por Israel Fernández Agudo

* El enunciado se ha resuelto de la forma en que se indica. He creado una API rest usando la
información que se me ha proporciando con la Api externa https://restcountries.com/. La Api conecta
a una base de datos local y devuelve en forma de Json la información reequerida.

# Apartado Técnico

### Lenguaje Java con el framework Springboot
He utilizado las siguientes dependecias con Maven:

* Conector Mysql
* Model Mapper
* Springboot Web (junto a test)
* JPA
* H2 (Para el testing)
* Project Lombok

### 
La base de datos usada es relacional con el gestor MySql y ejecutada de
manera local

### Caracaterísticas de la Aplicación

* Consulta a la base de datos y en caso de estar vacía conecta con https://restcountries.com/ y la rellena de países
* Nuevos datos pueden ser añadidos o actualizados gracias al endpoint POST
* Consulta de los países con el método GET (el json presenta una id, el nombre del páis en si y su población )
* Respuestas Http dinamicas en función a la tarea realizada
* El patrón de diseño se ha basado en la capas de Presentación, Servicio y Persistencia.(La capa de presentación no devuelve
entidades si no que se mapean a dtos para mantener separadas las responsabilidades de la apliación


## --English version:
# Backend tech interview trial from SDG Group
 Exercise made by Israel Fernández Agudo

* The statement has been resolved as the recruiters asked for. I built an Api Rest using information from an external
Api (https://restcountries.com/). My Application connects with a local database and retrieves the information in
json format.


# Tech used

### Java language alongside Springboot Framework
I used the next dependencies with Maven:

* Mysql Connector
* Model Mapper
* Springboot Web (Alongside SpringBoot Test)
* JPA
* H2 (For testing purposes)
* Project Lombok

### 
I used a MySql relational database executed in a local machine.

### Application features

* It checks the database if it is empty the app connect with https://restcountries.com/ and refill the database with countries.
* New data can be added using the POST endpoint.
* Check the countries names and their population using the GET endpoint (the retrieved json has an id,the country name and 
its population)
* It retrieves dynamic Http responses with the json object.
* The design pattern is based on the Presentation,Service and Persistence layers. Contrary to retrieve Entities it returns Dtos
as a good practice.




