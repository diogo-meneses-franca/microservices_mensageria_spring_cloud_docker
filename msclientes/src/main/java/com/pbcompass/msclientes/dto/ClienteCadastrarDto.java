package com.pbcompass.msclientes.dto;

import com.pbcompass.msclientes.entity.Cliente;
import lombok.Getter;

@Getter
public class ClienteCadastrarDto {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toCliente(){
        return new Cliente(null, nome, cpf, idade);
    }
}
