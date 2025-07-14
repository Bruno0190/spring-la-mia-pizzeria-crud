package org.java.spring.pizzeria.repository;

import org.java.spring.pizzeria.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

}
