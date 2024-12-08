package com.scortinas.tarjetasrest;

//import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.scortinas.tarjetasrest.domain.MarcaTarjeta;

@SpringBootApplication
public class TarjetasrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarjetasrestApplication.class, args);

		//LocalDate fecha = LocalDate.now();

        // double tasaVisa = MarcaTarjeta.VISA.calcularTasa(fecha);
        // double tasaNara = MarcaTarjeta.NARA.calcularTasa(fecha);
        // double tasaAmex = MarcaTarjeta.AMEX.calcularTasa(fecha);

        // System.out.println("Fecha actual: " + fecha);
        // System.out.println("Tasa VISA: " + tasaVisa);
        // System.out.println("Tasa NARA: " + tasaNara);
        // System.out.println("Tasa AMEX: " + tasaAmex);
	}

}
