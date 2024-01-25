package org.lesson.projectwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class Acquisto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataAcquisto;
   @NotNull
   private Integer quantità;
   @ManyToOne
   private Prodotto prodotto;

   private BigDecimal prezzoSingolo;

   private Integer codice;

   private BigDecimal prezzoTotale;

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

    public Integer getQuantità() {
        return quantità;
    }

    public void setQuantità(Integer quantità) {
        this.quantità = quantità;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public BigDecimal getPrezzoSingolo() {
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

    public BigDecimal getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(BigDecimal prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }
}
