package org.java.spring.pizzeria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obbligatorio")
    @Size(max = 20, message = "Max 20 caratteri")
    private String nome;

    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizze = new ArrayList<>();

    public Ingrediente(){
        
    }

    public Ingrediente(String nome, List<Pizza> pizze){
        this.nome = nome;
        this.pizze = pizze;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public Long getId() {
        return id;
    }

    public List<Pizza> getPizze() {
        return pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }



}


