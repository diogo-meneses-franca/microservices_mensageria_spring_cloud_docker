package com.pbcompass.msavaliadorcredito.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {

    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal limite;

}
