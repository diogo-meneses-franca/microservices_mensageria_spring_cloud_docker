package com.pbcompass.mscartoes.dto;

import com.pbcompass.mscartoes.entity.Bandeira;
import com.pbcompass.mscartoes.entity.Cartao;
import com.pbcompass.mscartoes.entity.CartaoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteDto {

    private String nome;
    private String bandeira;
    private BigDecimal limite;

    public CartoesPorClienteDto toDto(CartaoCliente cartao) {
        return new CartoesPorClienteDto(cartao.getCartao().getNome(), cartao.getCartao().getBandeira().toString(), cartao.getLimite());
    }

    public List<CartoesPorClienteDto> toDtoList(List<CartaoCliente> cartaoList) {
        return cartaoList.stream().map(cartao -> new CartoesPorClienteDto().toDto(cartao)).collect(Collectors.toList());
    }
}
