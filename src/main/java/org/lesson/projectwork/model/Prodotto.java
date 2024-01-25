package org.lesson.projectwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Table
@Entity
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String foto;
    @NotEmpty
    private String nome;
    private String descrizione;
    @NotNull
    @Min(1)
    private BigDecimal prezzo;
    private Integer quantità;
    @OneToMany(mappedBy = "prodotto")
    private List<Acquisto> acquisto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public List<Acquisto> getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(List<Acquisto> acquisto) {
        this.acquisto = acquisto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getQuantità() {
        return quantità;
    }

    public void setQuantità(Integer quantità) {
        this.quantità = quantità;
    }
    @PreRemove
    public void preRemove() {
        if (acquisto != null) {
            acquisto.forEach(a -> a.setProdotto(null));
        }
    }



}
