package org.java.spring.pizzeria.repository;

import org.java.spring.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


/*Mentre extends nella classi si usa per ereditare da una superclasse, nelle interfacce, extends eredita da un altra interfaccia. JpaRepository è un interfaccia già integrata in Spring */
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
