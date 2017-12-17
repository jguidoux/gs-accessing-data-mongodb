package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner insertDatas(CustomerRepository repository) throws Exception {
        return args -> {
            repository.deleteAll();

            // save a couple of customers
            Stream.of("Alice Smith", "Bob Smith")
                    .map(name -> name.split("\\s+", 2))
                    .map(name -> new Customer.CustomerBuilder().firstName(name[0]).lastName(name[1]).build())
                    .forEach(repository::save);


            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            repository.findAll().forEach(System.out::println);

            System.out.println();

            // fetch an individual customer
            System.out.println("Customer found with findByFirstName('Alice'):");
            System.out.println("--------------------------------");
            System.out.println(repository.findByFirstName("Alice"));

            System.out.println("Customers found with findByLastName('Smith'):");
            System.out.println("--------------------------------");
            repository.findByLastName("Smith").forEach(System.out::println);
        };
    }
}


