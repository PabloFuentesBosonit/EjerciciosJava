package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct // Es el primero e implementa en las dependencias
	private void springPostConstruct(){
		System.out.println("Hola desde la clase inicial");
	};

	@Bean// anotacion que pones a metodos cuando inicias y configuracion
	CommandLineRunner segunda(){
		return p -> {
			System.out.println("Hola desde la clase segunda");
		};
	}

	@Configuration
	public class tercera implements CommandLineRunner{
	@Override // anotacion que le dice que las cosas vienen de CommandLineRunner
	// (Aqui no hace falta porque estamos en interfaz)
		public void run(String...args) throws Exception{
			System.out.println("Hola desde la clase tercera");
		}
	};
}


