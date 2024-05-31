package com.pbcompass.msclientes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pbcompass.msclientes.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "nome", "cpf", "idade"})
public class ClienteRespostaDto extends RepresentationModel<ClienteRespostaDto> {

    @JsonProperty("id")
    private Long key;
    private String nome;
    private String cpf;
    private Integer idade;


    public ClienteRespostaDto(Cliente cliente) {
        this.key = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.idade = cliente.getIdade();
    }
}
