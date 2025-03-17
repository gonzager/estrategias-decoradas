package me.gonzager.ex.biblioteca;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Socio {
    private LocalDate fechaNacimiento;
    private LocalDate socioDesde;
    private BigDecimal deuda;

    public Socio(LocalDate fechaNacimiento, LocalDate socioDesde) {
        this.fechaNacimiento = fechaNacimiento;
        this.socioDesde = socioDesde;
        this.deuda = BigDecimal.ZERO;
    }

    public Boolean esMayorDeEdad() {
        return ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now()) >= 18L;
    }

    public Long antiguedad() {
        return ChronoUnit.MONTHS.between(socioDesde, LocalDate.now());
    }

    public LocalDate getSocioDesde() {
        return socioDesde;
    }

    public void setSocioDesde(LocalDate socioDesde) {
        this.socioDesde = socioDesde;
    }

    public Boolean tieneDeuda() {
        // ... WTF: Maldigo a Java que no tiene una forma mas decente de comparar
        // BigDecimal!!
        return deuda.compareTo(BigDecimal.ZERO) > 0;
    }

    public void aumentarDeuda(BigDecimal cuanto) {
        deuda = this.deuda.add(cuanto);
    }

    /**
     * Sobrecarga sencilla para aumentarDeuda.
     */
    public void aumentarDeuda(long cuanto) {
        this.aumentarDeuda(new BigDecimal(cuanto));
    }

    public void pagarDeuda() {
        deuda = BigDecimal.ZERO;
    }

    public BigDecimal getDeuda() {
        return deuda;
    }

}
