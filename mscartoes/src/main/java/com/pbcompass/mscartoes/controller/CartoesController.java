package com.pbcompass.mscartoes.controller;

import com.pbcompass.mscartoes.dto.CartaoDto;
import com.pbcompass.mscartoes.dto.CartoesPorClienteDto;
import com.pbcompass.mscartoes.entity.Cartao;
import com.pbcompass.mscartoes.entity.CartaoCliente;
import com.pbcompass.mscartoes.service.CartaoClienteService;
import com.pbcompass.mscartoes.service.CartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartoesController {

    private final CartaoService service;
    private final CartaoClienteService cartaoClienteService;

    @PostMapping
    public ResponseEntity<Cartao> cadastrar(@RequestBody CartaoDto dto){
        Cartao cartao = dto.toCartao();
        Cartao cartaoSalvo = service.cadastrar(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaoSalvo);

    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> buscarCartoesRendaMenorIgual(@RequestParam Long renda){
        List<Cartao> cartoes = service.buscarCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok().body(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteDto>> buscarCartoesPorCpf(@RequestParam("cpf") String cpf){
        List<CartaoCliente> cartaoClienteList = cartaoClienteService.buscarCartoesPorCpf(cpf);
        List<CartoesPorClienteDto> resposta = new CartoesPorClienteDto().toDtoList(cartaoClienteList);
        return ResponseEntity.ok().body(resposta);
    }
}
