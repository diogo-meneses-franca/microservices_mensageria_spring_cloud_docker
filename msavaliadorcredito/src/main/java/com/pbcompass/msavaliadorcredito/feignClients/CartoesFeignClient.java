package com.pbcompass.msavaliadorcredito.feignClients;

import com.pbcompass.msavaliadorcredito.entity.Cartao;
import com.pbcompass.msavaliadorcredito.entity.CartaoAprovado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesFeignClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoAprovado>> buscarCartoesPorCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> buscarCartoesRendaMenorIgual(@RequestParam Long renda);

}
