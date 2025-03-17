package me.gonzager.ex.prestamo;

import me.gonzager.ex.biblioteca.Socio;

public class ClasicoStrategy extends AnalizadorPrestamoDecorator {

    public ClasicoStrategy(AnalisisPrestamoStrategy analisisPrestamoStrategy) {
        super(analisisPrestamoStrategy);
    }

    @Override
    public Boolean condiconEspecifica(Socio socio) {
        return socio.antiguedad() > 6L;
    }

}
