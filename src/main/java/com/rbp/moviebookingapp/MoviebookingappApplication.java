package com.rbp.moviebookingapp;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.rbp.moviebookingapp.models.ERole;
import com.rbp.moviebookingapp.models.Movie;
import com.rbp.moviebookingapp.models.Role;
import com.rbp.moviebookingapp.repository.MovieRepository;
import com.rbp.moviebookingapp.repository.RoleRepository;


@SpringBootApplication
public class MoviebookingappApplication {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(MoviebookingappApplication.class, args);
	}

	@PostConstruct
	public void dataUpdate() {

		mongoTemplate.dropCollection("roles");
		mongoTemplate.dropCollection("ticket");
		mongoTemplate.dropCollection("users");
		mongoTemplate.dropCollection("movie");

		Movie movie1 = new Movie("Batman","Inox",36,"Book ASAP");
	 	Movie movie2 = new Movie("Wakanda","PVR",28,"Book ASAP");
	 	Movie movie3 = new Movie("Avenger","AGS",2,"Book ASAP");

	 	movieRepository.saveAll(List.of(movie1,movie2,movie3));

		Role admin = new Role(ERole.ROLE_ADMIN);
		Role user = new Role(ERole.ROLE_USER);

		roleRepository.saveAll(List.of(admin,user));
	}
}
