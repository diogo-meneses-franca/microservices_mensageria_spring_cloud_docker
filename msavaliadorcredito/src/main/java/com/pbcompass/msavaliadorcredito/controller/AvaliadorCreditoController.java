package com.pbcompass.msavaliadorcredito.controller;

import com.pbcompass.msavaliadorcredito.entity.DadosAvaliacao;
import com.pbcompass.msavaliadorcredito.entity.DadosCliente;
import com.pbcompass.msavaliadorcredito.entity.RetornoAvaliacaoCliente;
import com.pbcompass.msavaliadorcredito.entity.SituacaoCliente;
import com.pbcompass.msavaliadorcredito.service.AvaliadorCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService service;
    private final AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<SituacaoCliente> buscarSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = service.buscarSituacaoCliente(cpf);
        return ResponseEntity.ok().body(situacaoCliente);
    }

    @PostMapping
    public ResponseEntity<RetornoAvaliacaoCliente> avaliar(@RequestBody DadosAvaliacao dados){
        RetornoAvaliacaoCliente resposta = avaliadorCreditoService.avaliar(dados);
        return ResponseEntity.ok().body(resposta);
    }
}
