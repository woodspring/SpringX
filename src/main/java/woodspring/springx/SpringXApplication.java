package woodspring.springx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import woodspring.springx.repository.CustomerList;

@SpringBootApplication
public class SpringXApplication {

	public static void main(String[] args) {
		CustomerList.on().initCustomerList();
		SpringApplication.run(SpringXApplication.class, args);
	}
}
