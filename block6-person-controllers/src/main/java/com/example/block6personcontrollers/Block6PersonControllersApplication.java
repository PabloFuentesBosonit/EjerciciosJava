package com.example.block6personcontrollers;
import com.example.block6personcontrollers.clases.Persona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block6PersonControllersApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	@Bean("bean1")
	public Persona bean1() {
		String name = "Pablo";
		int age = 28;
		String town = "Granada";

		return new Persona(name,age,town);
	}
	@Bean("bean2")
	public Persona bean2() {
		String name = "Raul";
		int age = 27;
		String town = "Jaén";

		return new Persona(name,age,town);
	}
	@Bean("bean3")
	public Persona bean3() {
		String name = "Dario";
		int age = 37;
		String town = "Málaga";

		return new Persona(name,age,town);
	}

}
