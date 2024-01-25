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
    private String prodottoAcquistato;
    private Integer quantitàAcquistata;
    private String nomeFornitore;
    private BigDecimal prezzo;
    @ManyToOne
    private Prodotto prodotto;

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

    public String getProdottoAcquistato() {
        return prodottoAcquistato;
    }

    public void setProdottoAcquistato(String prodottoAcquistato) {
        this.prodottoAcquistato = prodottoAcquistato;
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

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
}