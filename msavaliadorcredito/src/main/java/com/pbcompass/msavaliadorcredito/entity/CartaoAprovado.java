package com.pbcompass.msavaliadorcredito.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoAprovado {

    private String cartao;
    private String bandeira;
    private BigDecimal limite;
}
