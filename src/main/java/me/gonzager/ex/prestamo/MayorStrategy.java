package me.gonzager.ex.prestamo;

import me.gonzager.ex.biblioteca.Socio;

public class MayorStrategy extends AnalizadorPrestamoDecorator {

    public MayorStrategy(AnalisisPrestamoStrategy analisisPrestamoStrategy) {
        super(analisisPrestamoStrategy);
    }

    @Override
    public Boolean condiconEspecifica(Socio socio) {
        return socio.esMayorDeEdad();
    }

}
