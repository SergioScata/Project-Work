package org.lesson.projectwork.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class Assortimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataAssortimento;
    private Integer quantitàAcquistata;
    private String nomeFornitore;
    private BigDecimal prezzoSingolo;
    private BigDecimal prezzoTotale;
    private String nome;



    @ManyToOne
    private Prodotto prodotto;
    public BigDecimal getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(BigDecimal prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAssortimento() {
        return dataAssortimento;
    }

    public void setDataAssortimento(LocalDate dataAssortimento) {
        this.dataAssortimento = dataAssortimento;
    }


    public Integer getQuantitàAcquistata() {
        return quantitàAcquistata;
    }

    public void setQuantitàAcquistata(Integer quantitàAcquistata) {
        this.quantitàAcquistata = quantitàAcquistata;
    }

    public String getNomeFornitore() {
        return nomeFornitore;
    }

    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }

    public BigDecimal getPrezzoSingolo() {
        return prezzoSingolo;
    }

    public void setPrezzoSingolo(BigDecimal prezzoSingolo) {
        this.prezzoSingolo = prezzoSingolo;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
