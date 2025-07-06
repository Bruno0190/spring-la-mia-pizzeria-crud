package org.java.spring.pizzeria.controller;

import org.java.spring.pizzeria.model.Pizza;
import org.java.spring.pizzeria.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/*L'annotazione @Controller serve dire che questa classe è quella che risponde alle richieste HTTP e restituisce pagine web. è il primo impatto con l'utente come un cameriere che ti serve un menu appena giunti al ristorante. */
@Controller
public class PizzaController {

    private PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

   /*L'annotazione @GetMapping serve a far intendere che questo metodo gestisce le richieste GET sull'URL"/". Più semplicemente: come l'utente si collega al sito localhost:8080 tramite la porta principale ("/") si attiva il seguente metodo*/
    @GetMapping("/")
    /*Il seguente metodo quindi che chiamiamo index prende come parametro un oggetto di tipo Model */
    public String index(Model model) {
        /*Tramite il metodo findAll() prendiamo tutti i modelli pizze nel database pizzaRepository e li ficchiamo in una lista Iterable.Una lista di cose? Ma di oggetti Pizza ovviamente */
        Iterable<Pizza> pizze = pizzaRepository.findAll(); 
        /*Con add.Attribute noi passiamo all'oggetto model del nostro metodo tutte le pizze e infine le ritornimao nell' .html che sta in templates. */    
        model.addAttribute("pizze", pizze);                   
        return "index";                                        
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizza/show";
    }
    
}

