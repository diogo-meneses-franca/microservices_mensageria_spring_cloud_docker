package com.pbcompass.msavaliadorcredito.controller;

import com.pbcompass.msavaliadorcredito.entity.*;
import com.pbcompass.msavaliadorcredito.exception.ErroSolicitacaoCartaoException;
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

    @PostMapping("/solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
        try {
            ProtocoloSolicitacaoCartao protocolo = avaliadorCreditoService.solicitarEmissaoCartao(dados);
            return ResponseEntity.ok().body(protocolo);
        }catch (ErroSolicitacaoCartaoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
