package me.gonzager.ex.prestamo;

import me.gonzager.ex.biblioteca.Socio;

public class DefaultStrategy extends AnalisisPrestamoStrategy {


    public Boolean puedePrestarse(Socio socio) {
        return Boolean.TRUE;
    }

}
