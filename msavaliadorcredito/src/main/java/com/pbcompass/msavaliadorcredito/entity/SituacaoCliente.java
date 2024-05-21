package com.pbcompass.msavaliadorcredito.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituacaoCliente {

    private DadosCliente cliente;
    private List<CartaoAprovado> cartoes;



}
