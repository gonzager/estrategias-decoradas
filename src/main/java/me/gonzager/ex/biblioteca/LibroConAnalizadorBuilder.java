package me.gonzager.ex.biblioteca;

import me.gonzager.ex.prestamo.AnalisisPrestamoStrategy;
import me.gonzager.ex.prestamo.ClasicoStrategy;
import me.gonzager.ex.prestamo.DefaultStrategy;
import me.gonzager.ex.prestamo.MayorStrategy;
import me.gonzager.ex.prestamo.SinDeudaStrategy;
import me.gonzager.ex.prestamo.UmbralDeuda;

public class LibroConAnalizadorBuilder {
    private String nombre;
    private AnalisisPrestamoStrategy analizador = new DefaultStrategy();

    public LibroConAnalizadorBuilder(String nombre) {
        this.nombre = nombre;
    }

    public LibroConAnalizadorBuilder adulto() {
        this.analizador = new MayorStrategy(analizador);
        return this;
    }

    public LibroConAnalizadorBuilder sinDeuda() {
        this.analizador = new SinDeudaStrategy(analizador);
        return this;
    }

    public LibroConAnalizadorBuilder clasico() {
        this.analizador = new ClasicoStrategy(analizador);
        return this;
    }

    public LibroConAnalizadorBuilder umbralDeuda() {
        this.analizador = new UmbralDeuda(analizador);
        return this;
    }

    public Libro build() {
        var libro = new Libro(this.nombre);
        libro.setAnalisisPrestamoStrategy(analizador);
        return libro;
    }

}
