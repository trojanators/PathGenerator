package pathgenerator.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * This class is for Initing & running the Spring boot web Page 
 * @author Nicholas Blackburn
 */

@SpringBootApplication
public class StartWebApp extends Thread{

    public void run() {
		SpringApplication.run(StartWebApp.class);
		
	}
}
