package org.java;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.java.spring.pizzeria.model.OffertaSpeciale;
import org.java.spring.pizzeria.model.Pizza;
import org.java.spring.pizzeria.repository.OffertaSpecialeRepository;
import org.java.spring.pizzeria.repository.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*Questa annotazione dice a Spring:"Ehi, questa è la classe principale, con questa parte inizi tutto il progetto Spring Boot". Include tante configurazioni automatiche (auto-configuration) per semplificarti la vita */
@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	/*Questa annotazione sopra un metodo serve a dire di creare questo oggetto e usarlo subito. CommandLineRunner é un'interfaccia speciale di Spring Boot: qualsiasi oggetto che la implementa verrà eseguito subito dopo l'avvio dell'app   */
	@Bean
	public CommandLineRunner loadData(PizzaRepository pizzaRepository, OffertaSpecialeRepository offertaSpecialeRepository) {
		/* return (args) -> è una lambda expression. (args) è il parametro del metodo run(...), cioè gli argomenti passati da riga di comando. → significa "fai questo con i parametri sopra". { ... } → è il corpo, cioè il codice che verrà eseguito */
		return (args) -> {
			//per le pizze
			Pizza margherita = new Pizza("Margherita", "Pomodoro, mozzarella, basilico", null, new BigDecimal("5.50"));
			Pizza diavola = new Pizza("Diavola", "Pomodoro, mozzarella, salame piccante", null, new BigDecimal("7.50"));
			Pizza capricciosa = new Pizza("Capricciosa", "Mozzarella, gorgonzola, parmigiano", null, new BigDecimal("8.50"));



			//per le offerte
			OffertaSpeciale offerta1 = new OffertaSpeciale("Promo Estate", LocalDate.of(2025, 8, 2), LocalDate.of(2025, 8, 10),margherita);
			OffertaSpeciale offerta2 = new OffertaSpeciale("Promo Autunno", LocalDate.of(2025, 10, 2), LocalDate.of(2025, 10, 20),margherita);

			margherita.addOfferta(offerta1);
			margherita.addOfferta(offerta2);


			pizzaRepository.save(margherita);
			pizzaRepository.save(diavola);
			pizzaRepository.save(capricciosa);



		};
	}

}
