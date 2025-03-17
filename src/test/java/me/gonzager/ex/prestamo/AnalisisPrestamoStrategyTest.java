package me.gonzager.ex.prestamo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import me.gonzager.ex.biblioteca.Libro;
import me.gonzager.ex.biblioteca.LibroConAnalizadorBuilder;
import me.gonzager.ex.biblioteca.Socio;

public class AnalisisPrestamoStrategyTest {

    // Fecha considerada con Actaul : 2025-03-01
    LocalDate fechaActual = LocalDate.of(2025, 3, 1);
    MockedStatic<LocalDate> mockedLocalDate;

    Socio socioMayorEdad = new Socio(
            LocalDate.of(1975, 12, 8),
            LocalDate.of(2025, 1, 2));

    Socio socioMenorEdad = new Socio(
            LocalDate.of(2015, 12, 8),
            LocalDate.of(2025, 1, 2));

    @BeforeEach
    void setUp() {
        // Mockeao LocalDate.now() para que devuelva una fecha fija evitando flaky test
        // CALLS_REAL_METHODS means only mock current method now
        mockedLocalDate = mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS);

        mockedLocalDate.when(LocalDate::now).thenReturn(fechaActual);

        socioMayorEdad.pagarDeuda();
        socioMenorEdad.pagarDeuda();
    }

    @AfterEach
    public void tearDown() {
        mockedLocalDate.close();
    }

    @Test
    void alAnalizarStrategyMayorAUnSocioMayor_sePuedePrestar() {
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .build();
        assertTrue(l.puedePrestarse(socioMayorEdad));
    }

    @Test
    void alAnalizarStrategyMayorAUnSocioMenor_noSePuedePrestar() {
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .build();
        assertFalse(l.puedePrestarse(socioMenorEdad));
    }

    @Test
    void alAnalizarStrategyMayorYSinDeudaAUnSocioMayorSinDeuda_sePuedePrestar() {
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .sinDeuda()
                .build();
        assertTrue(l.puedePrestarse(socioMayorEdad));
    }

    @Test
    void alAnalizarStrategyMayorYSinDeudaAUnSocioMayorDeEdadConDeuda_noSePuedePrestar() {
        socioMayorEdad.aumentarDeuda(10L);
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .sinDeuda()
                .build();
        assertFalse(l.puedePrestarse(socioMayorEdad));
    }

    @Test
    void alAnalizarStrategyMayorYSinDeudaYClasicoAUnSocioMayorSinDeudaConAntiguedad_sePuedePrestar() {
        socioMayorEdad.setSocioDesde(LocalDate.of(2024, 6, 1));
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .sinDeuda()
                .clasico()
                .build();
        assertTrue(l.puedePrestarse(socioMayorEdad));
    }

    @Test
    void alAnalizarStrategyMayorYSinDeudaYClasicoAUnSocioMayorSinDeudaSinAntiguedad_noSePuedePrestar() {
        socioMayorEdad.setSocioDesde(LocalDate.of(2025, 1, 1));
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .sinDeuda()
                .clasico()
                .build();
        assertFalse(l.puedePrestarse(socioMayorEdad));
    }

    @Test
    void alAnalizarStrategyMayorYUmbralDeudaYClasicoAUnSocioMayorDebajoUmbralDeudaConAntiguedad_sePuedePrestar() {
        socioMayorEdad.setSocioDesde(LocalDate.of(2024, 6, 1));
        UmbralDeuda.setUmbralDeuda(BigDecimal.valueOf(100));
        socioMayorEdad.aumentarDeuda(99);
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .umbralDeuda()
                .clasico()
                .build();
        assertTrue(l.puedePrestarse(socioMayorEdad));
    }

    @Test
    void alAnalizarStrategyMayorYUmbralDeudaYClasicoAUnSocioMayorEncimaUmbralDeudaConAntiguedad_noSePuedePrestar() {
        socioMayorEdad.setSocioDesde(LocalDate.of(2024, 6, 1));
        UmbralDeuda.setUmbralDeuda(BigDecimal.valueOf(100));
        socioMayorEdad.aumentarDeuda(101);
        Libro l = new LibroConAnalizadorBuilder("100 años de Soledad")
                .adulto()
                .umbralDeuda()
                .clasico()
                .build();
        assertFalse(l.puedePrestarse(socioMayorEdad));
    }

}
