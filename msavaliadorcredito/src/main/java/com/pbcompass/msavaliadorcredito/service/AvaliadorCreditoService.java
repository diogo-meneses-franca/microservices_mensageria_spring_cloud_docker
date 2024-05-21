package com.pbcompass.msavaliadorcredito.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pbcompass.msavaliadorcredito.entity.*;
import com.pbcompass.msavaliadorcredito.exception.DadosClienteNotFoundException;
import com.pbcompass.msavaliadorcredito.exception.ErroComunicacaoMicroservicosException;
import com.pbcompass.msavaliadorcredito.exception.ErroSolicitacaoCartaoException;
import com.pbcompass.msavaliadorcredito.feignClients.CartoesFeignClient;
import com.pbcompass.msavaliadorcredito.feignClients.ClienteFeignClient;
import com.pbcompass.msavaliadorcredito.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteFeignClient clienteFeignClient;
    private final CartoesFeignClient cartoesFeignClient;
    private final SolicitacaoEmissaoCartaoPublisher publisher;

    public SituacaoCliente buscarSituacaoCliente(String cpf){
        try {
            ResponseEntity<List<CartaoAprovado>> cartoes = cartoesFeignClient.buscarCartoesPorCpf(cpf);
            ResponseEntity<DadosCliente> cliente = clienteFeignClient.buscarPorCpf(cpf);
            return SituacaoCliente
                    .builder()
                    .cliente(cliente.getBody())
                    .cartoes(cartoes.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(status == HttpStatus.NOT_FOUND.value()){
                throw new DadosClienteNotFoundException("Dados do cliente não encontrados para o cpf: " + cpf);
            }
            throw new ErroComunicacaoMicroservicosException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente avaliar(DadosAvaliacao dados){
        DadosCliente cliente = clienteFeignClient.buscarPorCpf(dados.getCpf()).getBody();
        List<Cartao> cartoes = cartoesFeignClient.buscarCartoesRendaMenorIgual(dados.getRenda()).getBody();
        var listaDeCartoesAprovados = cartoes.stream().map( cartao -> {
            BigDecimal limiteBasico = cartao.getLimite();
            BigDecimal idadeBd = BigDecimal.valueOf(cliente.getIdade());
            var fator = idadeBd.divide(BigDecimal.valueOf(10));
            BigDecimal limiteAprovado = fator.multiply(limiteBasico);

            CartaoAprovado cartaoAprovado = new CartaoAprovado();
            cartaoAprovado.setNome(cartao.getNome());
            cartaoAprovado.setBandeira(cartao.getBandeira());
            cartaoAprovado.setLimite(limiteAprovado);

            return cartaoAprovado;

        }).collect(Collectors.toList());

        return new RetornoAvaliacaoCliente(listaDeCartoesAprovados);

    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados){
        try {
            publisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        }catch (Exception e){
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
