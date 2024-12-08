package com.scortinas.tarjetasrest.domain;

import java.time.LocalDate;

//Enum para las tasas. Como son pocas y limitadas se utiliza un enum, si escala o se desean agregar en tiempo de ejecución considerar agregar una clase.
 
public enum MarcaTarjeta {
    VISA {
        @Override
        public double calcularTasa(LocalDate fecha) {
            return fecha.getYear() / (double) fecha.getMonthValue();
        }
    },
    NARA {
        @Override
        public double calcularTasa(LocalDate fecha) {
            return fecha.getDayOfMonth() * 0.5;
        }
    },
    AMEX {
        @Override
        public double calcularTasa(LocalDate fecha) {
            return fecha.getMonthValue() * 0.1;
        }
    };

    public abstract double calcularTasa(LocalDate fecha);
}
