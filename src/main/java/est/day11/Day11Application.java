package est.day11;

import est.day11.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day11Application {

	public static void main(String[] args) {

		SpringApplication.run(Day11Application.class, args);
	}

}
