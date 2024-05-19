package com.pbcompass.mscartoes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table
@Data
@NoArgsConstructor
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao(String nome, Bandeira bandeira, BigDecimal renda, BigDecimal limite) {
        this.bandeira = bandeira;
        this.limite = limite;
        this.nome = nome;
        this.renda = renda;
    }
}
