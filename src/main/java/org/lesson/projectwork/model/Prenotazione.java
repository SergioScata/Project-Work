package org.lesson.projectwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
public class Prenotazione{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataAcquisto;
    @NotNull
    private Integer quantita;
    @ManyToOne
    private Prodotto prodotto;

    private BigDecimal prezzoSingolo;

    private Integer codice;


    private String nome;
    @ManyToOne
    private MuseumUser user;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public BigDecimal getPrezzoSingolo()
    {
        return prezzoSingolo;
    }

    public void setPrezzoSingolo(BigDecimal prezzoSingolo) {
        this.prezzoSingolo = prezzoSingolo;
    }

    public Integer getCodice() {
        return codice;
    }

    public void setCodice(Integer codice) {
        this.codice = codice;
    }

    public MuseumUser getUser() {
        return user;
    }

    public void setUser(MuseumUser user) {
        this.user = user;
    }

    public  BigDecimal getPrezzoTotale(){
        BigDecimal prezzoTotale= prezzoSingolo.multiply(new BigDecimal(quantita));

        return prezzoTotale;
    }
}

