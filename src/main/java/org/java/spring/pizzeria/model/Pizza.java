package org.java.spring.pizzeria.model;

import java.math.BigDecimal;
/*jakarta.perstistence è il pacchetto di annotazioni che si usano nel codice per dire a JPA cosa ritenere cosa e come. JavaPersistenceAPI è una specifica che serve a "mappare" oggetti JAVA a tabelle di DATABASE e gestire operazioni CRUD (Create, Read, Update, Delete). ATTENZIONE: affinchè si possano importare determinati pacchetti può essere necessario, come in questo caso, modificare il pom.xml aggiungendo dependecies come <groupId>org.springframework.boot</groupId> - <artifactId>spring-boot-starter-data-jpa</artifactId>*/
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


/*Le annotazioni con la chiocciola servono a indicare a Spring come deve trattare quelle parti di codice per l'appunto "annotate". Entity serve a dire che la classe Pizza è una tabella nel database. JPA mappa questa classe ad una tabella */
@Entity
public class Pizza {

    private String nome;
    private String descrizione;
    private String foto;
    private BigDecimal prezzo;

    /*Id serve a dire che private Long id è una chiave primaria della tabella */
    @Id
    /*La seguente annotazione standard indica che il valore id sarà generato automaticamente, quindi autoincrementato ogni volta che si aggiorna la tabella */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


}
