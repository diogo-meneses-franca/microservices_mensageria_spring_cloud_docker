package com.pbcompass.msclientes.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pbcompass.msclientes.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"nome", "cpf", "idade"})
public class ClienteCadastrarDto {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toCliente(){
        return new Cliente(nome, cpf, idade);
    }
}
