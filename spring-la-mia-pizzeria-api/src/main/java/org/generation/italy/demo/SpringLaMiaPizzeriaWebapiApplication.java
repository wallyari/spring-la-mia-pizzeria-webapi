package org.generation.italy.demo;

 import java.util.List;

 import org.generation.italy.demo.pojo.Pizza;
 import org.generation.italy.demo.service.PizzaService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;

 @SpringBootApplication
 public class SpringLaMiaPizzeriaWebapiApplication implements CommandLineRunner{

 	@Autowired
 	private PizzaService pizzaService;

 	public static void main(String[] args) {
 		SpringApplication.run(SpringLaMiaPizzeriaWebapiApplication.class, args);
 	}

 	@Override
 	public void run(String... args) throws Exception {

 		Pizza p1 = new Pizza("Margherita", "Pomodoro, mozzarella, basilico");
		Pizza p2 = new Pizza("Marinara", "Pomodoro, olio, origano");		
		Pizza p3 = new Pizza("Capricciosa", "Pomodoro, mozzarella, funghi");
		Pizza p4 = new Pizza("Boscaiola", "Pomodoro, mozzarella, funghi, olive");

 		pizzaService.save(p1);
 		pizzaService.save(p2);
 		pizzaService.save(p3);
 		pizzaService.save(p4);

 		List<Pizza> pizzas =pizzaService.findAll();

 		System.err.println(pizzas);
 	}

 }