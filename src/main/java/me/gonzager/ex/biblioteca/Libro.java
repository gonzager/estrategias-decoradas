package me.gonzager.ex.biblioteca;

import me.gonzager.ex.prestamo.AnalisisPrestamoStrategy;

public class Libro {
    private String nombre;
    private AnalisisPrestamoStrategy analisisPrestamoStrategy;

    public Libro(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Me pasan la politica que va a decidir si me puedo prestar o no.
     */
    public void setAnalisisPrestamoStrategy(AnalisisPrestamoStrategy analisisPrestamoStrategy) {
        this.analisisPrestamoStrategy = analisisPrestamoStrategy;
        analisisPrestamoStrategy.setLibro(this);
    }

    /**
     * Me preguntan si me puedo prestar o no a un determinado usuario.
     */
    public Boolean puedePrestarse(Socio socio) {
        return this.analisisPrestamoStrategy.puedePrestarse(socio);
    }

}
