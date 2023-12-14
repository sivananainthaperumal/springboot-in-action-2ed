package com.dsp.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping(value = "hello")
public class HelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@GetMapping(value = "/{firstName}")
	public String helloGet(
			@PathVariable("firstName") String firstName,
			@RequestParam("lastName") String lastName){
		return String.format("Hello %s %s",firstName,lastName);
	}

}
