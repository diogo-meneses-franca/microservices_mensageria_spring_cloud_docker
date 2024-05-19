package com.pbcompass.mscartoes.service;

import com.pbcompass.mscartoes.entity.Cartao;
import com.pbcompass.mscartoes.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao cadastrar(Cartao cartao) {
        return repository.save(cartao);
    }

    @Transactional
    public List<Cartao> buscarCartoesRendaMenorIgual(Long renda){
        BigDecimal rendaBigdecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThenEqual(rendaBigdecimal);
    }
}
