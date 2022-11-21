package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		System.setProperty("new.property", "funciona");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Component
	public class Componente implements CommandLineRunner{

		@Value("${greeting}") private String nombre;
		@Value("${myNumber}") private String numero;
		@Value("${new.property}") private String newProperty;



		@Override public void run(String... args) throws Exception {
			System.out.println("El valor de greeting es: " + nombre);
			System.out.println("El valor de myNumber es: " + numero);
			System.out.println("El valor de new.property es: " + newProperty);
		}
	}

}
