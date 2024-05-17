package com.pbcompass.msclientes.dto;

import com.pbcompass.msclientes.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCadastrarDto {

    private String nome;
    private String cpf;
    private Integer idade;

    public Cliente toCliente(){
        return new Cliente(nome, cpf, idade);
    }
}
