package com.pbcompass.mscartoes.service;

import com.pbcompass.mscartoes.entity.CartaoCliente;
import com.pbcompass.mscartoes.repository.CartaoClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoClienteService {

    private final CartaoClienteRepository repository;

    public List<CartaoCliente> buscarCartoesPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
