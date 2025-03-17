package me.gonzager.ex.prestamo;

import java.math.BigDecimal;

import me.gonzager.ex.biblioteca.Socio;

public class UmbralDeuda extends AnalizadorPrestamoDecorator {

    private static BigDecimal umbralDeuda = new BigDecimal(0);

    public UmbralDeuda(AnalisisPrestamoStrategy analisisPrestamoStrategy) {
        super(analisisPrestamoStrategy);
    }

    @Override
    public Boolean condiconEspecifica(Socio socio) {
        return socio.getDeuda().compareTo(umbralDeuda) <= 0;
    }

    public static BigDecimal getUmbralDeuda() {
        return umbralDeuda;
    }

    public static void setUmbralDeuda(BigDecimal umbralDeuda) {
        UmbralDeuda.umbralDeuda = umbralDeuda;
    }
}
