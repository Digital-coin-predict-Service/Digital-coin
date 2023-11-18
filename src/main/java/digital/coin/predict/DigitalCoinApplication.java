package digital.coin.predict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DigitalCoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalCoinApplication.class, args);
	}

}
