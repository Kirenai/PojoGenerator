package me.kire.re.pojogenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PojoGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PojoGeneratorApplication.class, args);
	}

}
