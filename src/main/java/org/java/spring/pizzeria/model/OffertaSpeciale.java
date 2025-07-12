package org.java.spring.pizzeria.model;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class OffertaSpeciale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obbligatorio")
    @Size(max = 100, message = "Max 100 caratteri")
    private String titolo;
    
    @FutureOrPresent(message = "La data deve essere odierna o futura")
    private LocalDate dataInizio;
    @Future(message = "La data deve essere futura")
    private LocalDate dataFine;

    /*L'annotazione sottostante vuol dire che questa tabella OffertaSpeciale è collegata ad una e una sola tabella (pizza in questo caso). Essendo Molti a UNO vuol dire che altre tabelle di questo tipo potranno essere collegate alla stessa tabella pizza */
    @ManyToOne
    /*Si aggiunga una colonna in cui sono riportare i foreign key pizza_id. Non potranno essere collegati a tabelle pizza inesistenti. */
    @JoinColumn(name="pizza_id", nullable = false)
    private Pizza pizza;

    public OffertaSpeciale(){

    }

    public OffertaSpeciale(String titolo, LocalDate dataInizio, LocalDate dataFine, Pizza pizza){

        this.titolo = titolo;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.pizza = pizza;

    }

    public Long getId(){

        return id;
    }

    public String getTitolo(){

        return titolo;
    }

    public LocalDate getDataInizio(){

        return dataInizio;
    }

    public LocalDate getDataFine(){

        return dataFine;
    }

    public Pizza getPizza(){

        return pizza;
    }

    public void setTitolo(String titolo){

        this.titolo = titolo;
    }

    public void setDataInizio(LocalDate dataInizio){

        this.dataInizio = dataInizio;
    }

    public void setDataFine(LocalDate dataFine){

        this.dataFine = dataFine;
    }

    public void setPizza(Pizza pizza){

        this.pizza = pizza;
    }


}



