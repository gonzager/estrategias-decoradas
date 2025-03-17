package me.gonzager.ex.prestamo;

import me.gonzager.ex.biblioteca.Socio;

public class SinDeudaStrategy extends AnalizadorPrestamoDecorator {

    public SinDeudaStrategy(AnalisisPrestamoStrategy analisisPrestamoStrategy) {
        super(analisisPrestamoStrategy);
    }

    @Override
    public Boolean condiconEspecifica(Socio socio) {
        return !socio.tieneDeuda();
    }

}
