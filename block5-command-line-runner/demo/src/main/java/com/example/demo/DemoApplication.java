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

	/* Modificar la tercera funcion para que imprima los valores pasados como parámetro

	@Bean
	public class Saludo {
		protected String s1 = "Soy la";
		protected String s2 = "tercera clase";
	}

	@Bean public String get1(){
		return s1;
	}

	@Bean public String get2(){
		return s2;
	}

	Override
	public void run(String...args) throws Exception{
		System.out.println(s1 + s2);
	};

	*/
}


