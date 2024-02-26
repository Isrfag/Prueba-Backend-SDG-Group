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

* Consulta a la base de datos y en caso de estar vacía conecta con https://restcountries.com/ y la relle
na de países
* Nuevos datos pueden ser añadidos o actualizados gracias al endpoint POST
* Consulta de los países con el método GET (el json presenta una id, el nombre del páis en si y su población )
* Respuestas Http dinamicas en función a la tarea realizada
* El patrón de diseño se ha basado en la capas de Presentación, Servicio y Persistencia.(La capa de presentación no devuelve
entidades si no que se mapean a dtos para mantener separadas las responsabilidades de la apliación




