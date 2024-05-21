package com.pbcompass.mscartoes.dto;

import com.pbcompass.mscartoes.entity.Bandeira;
import com.pbcompass.mscartoes.entity.Cartao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDto {

    private String nome;
    private Bandeira bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toCartao(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
