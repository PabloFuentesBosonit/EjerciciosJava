package com.example.block16trip;

import com.example.block16trip.cliente.domain.Client;
import com.example.block16trip.cliente.repository.ClientRepository;
import com.example.block16trip.viaje.domain.Trip;
import com.example.block16trip.viaje.repository.TripRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block16TripApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16TripApplication.class, args);
	}

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	TripRepository tripRepository;

	@PostConstruct
	public void createDataBase(){
		Client client = new Client();
		client.setName("Pablo");
		client.setSurname("Fuentes Quirosa");
		client.setEmail("pablo@.com");
		client.setAge(29);
		client.setPhone("645119999");

		clientRepository.save(client);

		Client client2 = new Client();
		client2.setName("Nacho");
		client2.setSurname("Fuentes Quirosa");
		client2.setEmail("nacho@.com");
		client2.setAge(25);
		client2.setPhone("645119944");

		clientRepository.save(client2);

		Trip trip = new Trip();
		trip.setOrigin("Morocco");
		trip.setDestination("Dubai");
		trip.setDepartureDate("15-01-2023");
		trip.setArrivalDate("12-01-2023");
		trip.setStatus(true);

		tripRepository.save(trip);
	}

}
