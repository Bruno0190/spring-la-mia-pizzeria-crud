package org.java.spring.pizzeria.controller;

import org.java.spring.pizzeria.model.Ingrediente;
import org.java.spring.pizzeria.model.OffertaSpeciale;
import org.java.spring.pizzeria.model.Pizza;
import org.java.spring.pizzeria.repository.IngredienteRepository;
import org.java.spring.pizzeria.repository.OffertaSpecialeRepository;
import org.java.spring.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;


/*L'annotazione @Controller serve dire che questa classe è quella che risponde alle richieste HTTP e restituisce pagine web. è il primo impatto con l'utente come un cameriere che ti serve un menu appena giunti al ristorante. */
@Controller
public class PizzaController {

    private PizzaRepository pizzaRepository;
    /*Uso Autowired per l'attributo OffertaSpeciale perchè l'ho inserito successivamente e questa notazione mi consente di non doverlo inserire nel costruttore. Spring cercherà automaticamente un bean di tipo OffertaSpecialeRepository e lo inserirà. */
    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

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
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        model.addAttribute("pizza", pizza);
        return "pizza/show";
    }

    @GetMapping("/pizza/create")
    public String create(Model model){

        model.addAttribute("pizza", new Pizza());
        
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "pizza/create";
    }

    @PostMapping("/pizza/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            return "pizza/create";
        }
        
        pizzaRepository.save(pizza);
        
        return "redirect:/";
    }

    @GetMapping("/offerte/create")
    public String createOfferta(Model model){

        model.addAttribute("offerta", new OffertaSpeciale());
        // Passa la lista pizze al modello
        model.addAttribute("pizze", pizzaRepository.findAll());  

        return "offerte/create";
    }

    @PostMapping("/offerte/create")
    public String store(
        @Valid @ModelAttribute("offerta") OffertaSpeciale offerta,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pizze", pizzaRepository.findAll()); // Ricarica la lista in caso di errore
            return "offerte/create";
        }

        // Recupera la pizza dal suo ID
        Long pizzaId = offerta.getPizza().getId(); // Assumendo che solo l'id sia stato inviato
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        offerta.setPizza(pizza); // Associa esplicitamente

        offertaSpecialeRepository.save(offerta);

        return "redirect:/";
    }

    @GetMapping("/ingredienti")
    public String indexIngredienti(Model model) {
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "ingredienti/index";
    }


    @GetMapping("/ingredienti/create")
    public String createIngrediente(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        model.addAttribute("pizze", pizzaRepository.findAll());  // <-- qui passi tutte le pizze!
        return "ingredienti/create";
    }

    @PostMapping("/ingredienti/create")
    public String storeIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredienti/create";
        }
        ingredienteRepository.save(ingrediente);
        return "redirect:/ingredienti";
    }

    
    
}

