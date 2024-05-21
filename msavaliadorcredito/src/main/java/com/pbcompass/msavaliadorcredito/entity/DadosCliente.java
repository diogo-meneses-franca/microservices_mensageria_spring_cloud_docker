package com.pbcompass.msavaliadorcredito.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCliente {

    private Long id;
    private String nome;
    private Integer idade;

}
