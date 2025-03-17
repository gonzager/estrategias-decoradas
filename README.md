# Préstamo de Libros

![Libros](./img/libro.jpg)

## Contexto

En esta biblioteca, los socios pueden solicitar préstamos de libros. Para aprobar un préstamo, se deben analizar ciertas condiciones que determinan si el libro puede ser prestado o no. Nuestro objetivo es modelar este proceso de análisis y validación de préstamos.

## Condiciones de un préstamo

Las reglas que debemos modelar son las siguientes:

- Para algunos libros el socio debe estar al día, es decir no debe tener deuda.
- Para otros libros la deuda del socio debe estar por debajo de un umbral único y que dicho valor se puede ir ajustando con el tiempo.
- Para libros de adultos el socio debe ser mayor de edad.
- Los libros clásicos solo pueden ser prestados si el socio tiene una antigüedad de 6 meses o más.

## Analizador de Préstamos

Tener en cuenta que los libros pueden combinar las condiciones. Por ejemplo, para el libro “Cien Años de Soledad del autor Colombiano Gabriel Garcia Marquez” la biblioteca desea analizar el préstamo como un clásico de adultos para socios que no tienen deudas. Otra cuestión muy importante a tener en cuenta es que el analizador a este libro puede cambiarse en cualquier momento cuando la biblioteca lo decida y por ejemplo el mismo libro ahora lo quieren comenzar a analizar como clásico que puede se prestan a lo socios que tiene deuda pero que no superan el umbral independientemente de la edad que tenga el socio.

## Ayudas

Respectar la siguientes decisiones de diseño:

- Representar en objetos individuales y explícitos cada condiciones que podría hacer que el socio se se puede llevar un determinado libro.
- Encadenar las condiciones, cada cóndicion es una capa que se agrega al resto. Al final de la cadena tengo que poner una condición que siempre sea verdadera.
- La intefaz para interactuar con el objeto libro es la siguiente, hay que **respetarla!!** y no agregar más comportamientos.

  ```bash
    /**
    * Me pasan la politica que va a decidir si me puedo prestar o no.
    */
    public void setAnalizadorPrestamos(AnalisisPrestamo analizadorPrestamo)

    /**
    * Me preguntan si me puedo prestar o no a un determinado socio.
    */
    public Boolean puedePrestarse(Socio socio)


    /**
    * Recupera el nombre del Libro.
    */
    public String getNombre()
  ```

- (\*) Reificar los conceptos clasico, umbral deuda, sin deuda, etc para lograr construir los Libros a través de un builder que puede configurar el analizador.
- Tener covertura de test de mas del 90% del código.

(\*) Reificar: tratar un proceso o una propiedad como si fuera una cosa. En el contexto del diseño es encontrar una abstracción y darle entidad (a través de un objeto, una clase o un método)
