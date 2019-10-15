package drylo.tech.esoscrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsoScrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsoScrapApplication.class, args);
    }

}
