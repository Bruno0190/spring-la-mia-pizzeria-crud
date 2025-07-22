package org.java.spring.pizzeria.repository;

import org.java.spring.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


/*Mentre extends nella classi si usa per ereditare da una superclasse, nelle interfacce, extends eredita da un altra interfaccia. JpaRepository è un interfaccia già integrata in Spring.
In sostanza i file marcati come Repository sono delle interfacce Java perchè l'interfaccia è in effetti un file java che definisce quali metodi devono esserci senza scriverli. Infatti estendendo JpaRepository<Classe, attributo di riconoscimento> quest'interfaccia va ad implementare tutti i metodi
- .findAll();
- .findById(ID);
- .save(classeObj);
- .deleteById(ID); */
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
