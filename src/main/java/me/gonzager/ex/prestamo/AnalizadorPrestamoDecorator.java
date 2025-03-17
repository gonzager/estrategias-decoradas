package me.gonzager.ex.prestamo;

import me.gonzager.ex.biblioteca.Socio;

public abstract class AnalizadorPrestamoDecorator extends AnalisisPrestamoStrategy {

    protected AnalisisPrestamoStrategy decoratedStrategy;

    public AnalizadorPrestamoDecorator(AnalisisPrestamoStrategy analisisPrestamoStrategy) {
        this.decoratedStrategy = analisisPrestamoStrategy;
    }

    @Override
    public Boolean puedePrestarse(Socio socio) {
        return this.condiconEspecifica(socio) && decoratedStrategy.puedePrestarse(socio);
    }

    public abstract Boolean condiconEspecifica(Socio socio);

}
