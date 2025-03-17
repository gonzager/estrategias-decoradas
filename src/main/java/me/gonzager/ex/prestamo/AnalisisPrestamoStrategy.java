package me.gonzager.ex.prestamo;

import me.gonzager.ex.biblioteca.Libro;
import me.gonzager.ex.biblioteca.Socio;

public abstract class AnalisisPrestamoStrategy {
    private Libro libro;

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Libro getLibro() {
        return libro;
    }

    public abstract Boolean puedePrestarse(Socio socio);

}
