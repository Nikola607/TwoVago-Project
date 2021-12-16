package personal.project.two_vago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwoVagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoVagoApplication.class, args);
    }

}
