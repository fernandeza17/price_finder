# Price Finder Application

## Presentación
Este repositorio contiene la solución para la prueba técnica requerida por BCNC Consulting Group. 

## Sobre el diseño

Se ha optado por implementar la solución siguiendo el diseño de arquitectura hexagonal.

Siguiendo dicho diseño, consideraremos dos partes principales: el hexágono en sí y fuera del mismo.

Dentro del hexágono tendremos dos capas principales, *application* y *domain*. La capa *application* es donde se encontrarán los puertos, esto es, la parte del hexágono que tendrá contacto con el exterior. En la capa del *domain* encontraremos la lógica de negocio, además de los objetos claves del modelo del dominio. El principio fundamental es que todo el hexágono sea autosuficiente y no requiera de configuración o dependencias externas.
- Aquí cabe señalar que se podría considerar que se está utilizando una dependencia externa al hacer uso de *lombok* pero suele ser un standard habitual en este tipo de diseños
- No se han seguido los principios DDD en la implementación del dominio, que conllevaría el uso de entidades, *value objects* y *root aggregates* debido a falta de experiencia con dicha implementación.

En el exterior del hexágono tendremos la capa de infraestructura, que proveerá configuración para ejecutar el servicio, así como la implementación concreta en forma de *adapters*. Estas piezas tienen contacto con los puertos definidos en la capa *application*.

## Sobre las tecnologías

En este apartado describiremos las tecnologías usadas para la solución.

### Configuración general y base de datos

En este caso se ha construido el servicio con Spring Boot y usando una base de datos *in-memory* H2.
Al arrancar la applicación que podemos encontrar en `src/main/java/com/fernandeza/price_finder/PriceFinderApplication.java se ejecutará el servidor y se precargarán los datos de ejemplo que fueron en el archivo .txt.

### Implementación de la entrada

Para la implementación de entrada se ha usado un servicio de API Rest usando SpringBoot.
La estructura de paquetes elegida permite una clara visión de diferentes adaptadores de entrada en caso de que hubieran varios.
Por ejemplo si quisieramos implementar un adaptador de entrada en forma de un *listener* de eventos crearíamos un nuevo paquete bajo *infrastructure.adapters.input.listener*, al igual que el actual es *infrastructure.adapters.input.rest*.

Para el mapeo entre los objetos utilizados en el adaptador Rest y los del dominio se ha utiizado la librería Mapstruct.

```xml
	<dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${org.mapstruct.version}</version>
        </dependency>
```

El endpoint creado es <a>/v1/prices/{date}/{brandId}/{productId}</a>.

En nuestro caso esperará el parámetro de la fecha con el patrón "yyyy-MM-dd-HH.mm.ss" tal y como se proporcionan estas en el archivo .txt.

En caso de no obtener resultados se ha optado por la creación de una excepción propia PriceNotFoundException.
```java
	throw new PriceNotFoundException("Price not found");
```

### Implementación de la salida

Para la implementación de salida se ha utilizado la persistencia de Spring JPA.
Similar a la entrada la estructura de paquetes elegida permite una clara visión de diferentes adaptadores de salida en caso de que hubieran varios.
Por ejemplo si quisieramos implementar un adaptador de salida basado en *MongoDB* crearíamos un nuevo paquete bajo *infrastructure.adapters.output.mongo*.

Para el mapeo entre los objetos utilizados en el adaptador de persistencia y los del dominio se ha utiizado de nuevo la librería Mapstruct.

## Tests

Para ejecutar los tests simplemente tenemos que ejecutar la clase `src/test/java/com/fernandeza/price_finder/integration/PriceRestTest.java` que a su vez arrancará el servidor de Spring Boot y precargará la base de datos.

Se ha optado por la implementación a través de mocks usando la librería Mockito. Se ha creado un caso por cada petición en el archivo .txt

> Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)

- Espera como resultado el elemento de la tabla PRICES con PRICE_LIST 1. (`testFindPriceCase1410`)

> Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

- Espera como resultado el elemento de la tabla PRICES con PRICE_LIST 2 (aplicando así correctamente la prioridad) (`testFindPriceCase1416`)

> Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

- Espera como resultado el elemento de la tabla PRICES con PRICE_LIST 1 (`testFindPriceCase1421`)

> Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

- Espera como resultado el elemento de la tabla PRICES con PRICE_LIST 3 (`testFindPriceCase1510`)

> Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

- Espera como resultado el elemento de la tabla PRICES con PRICE_LIST 4 (`testFindPriceCase1621`)