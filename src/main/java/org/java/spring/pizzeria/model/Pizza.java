package org.java.spring.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;
/*jakarta.perstistence è il pacchetto di annotazioni che si usano nel codice per dire a JPA cosa ritenere cosa e come. JavaPersistenceAPI è una specifica che serve a "mappare" oggetti JAVA a tabelle di DATABASE e gestire operazioni CRUD (Create, Read, Update, Delete). ATTENZIONE: affinchè si possano importare determinati pacchetti può essere necessario, come in questo caso, modificare il pom.xml aggiungendo dependecies come <groupId>org.springframework.boot</groupId> - <artifactId>spring-boot-starter-data-jpa</artifactId>*/
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;

import jakarta.persistence.OneToMany;


/*Le annotazioni con la chiocciola servono a indicare a Spring come deve trattare quelle parti di codice per l'appunto "annotate". Entity serve a dire che la classe Pizza è una tabella nel database. JPA mappa questa classe ad una tabella */
@Entity

/*Questa notazione vuol dire solamente che la classe sottostante è la tabella nominata pizza nel database */
@Table(name= "pizza")
public class Pizza {

    @NotBlank(message = "Nome obbligatorio")
    @Size(max = 20, message = "Max 20 caratteri")
    private String nome;
    @NotBlank(message = "Descrizione obbligatoria")
    @Size(max = 100, message = "Max 100 caratteri")
    private String descrizione;
    private String foto;
    @NotNull(message = "Tutto ha un costo")
    @DecimalMin(value = "0.50", message = "Prezzo minimo 50 centesimi")
    private BigDecimal prezzo;

    /*Id serve a dire che private Long id è una chiave primaria della tabella */
    @Id
    /*La seguente annotazione standard indica che il valore id sarà generato automaticamente, quindi autoincrementato ogni volta che si aggiorna la tabella */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*L'annotazione sottostante sta ad indicare un rapporto 1 a Molti con un altra tabella. In particolare vuol dire che questa tabella (pizza) da sola ha più collegamenti con le tabelle offerteSpeciali (raccolte qui in un oggetto Lista) che sono un altro tipo di tabella/classe OfferstaSpeciale. mappedBy significa che il foreign key sta dall'altra parte (nell'altra tabella collegato tramite pizza.). CascadeType invece è un modo per non dover gestire manualmente le offerte nel controller, vengono sincronizzate le operazioni di salva, cancella e aggiorna. */
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<OffertaSpeciale> offerteSpeciali = new ArrayList<>();

    /*Quando Spring crea un oggetto Pizza partendo da questa classe, lo crea senza parametri perchè glieli inietta successivamente con i setter. Per tale motivo il costruttore è vuoto. Obbligatorio per JPA */
    public Pizza(){

    }
    /*Il secondo costruttore invece ha dei paramtri che occorrono per creare istanze con tutti i valori */
    public Pizza(String nome, String descrizione, String foto, BigDecimal prezzo){

        this.nome = nome;
        this.descrizione = descrizione;
        this.foto = foto;
        this.prezzo = prezzo;
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public String getFoto(){
        return foto;
    }

    public BigDecimal getPrezzo(){
        return prezzo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    // getter e setter per offerte
    public List<OffertaSpeciale> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OffertaSpeciale> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }

    public void addOfferta(OffertaSpeciale offerta) {
    offerteSpeciali.add(offerta);
    offerta.setPizza(this);
}


}
