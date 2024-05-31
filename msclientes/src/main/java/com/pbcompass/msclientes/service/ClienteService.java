package com.pbcompass.msclientes.service;

import com.pbcompass.msclientes.controller.ClienteController;
import com.pbcompass.msclientes.dto.ClienteCadastrarDto;
import com.pbcompass.msclientes.dto.ClienteRespostaDto;
import com.pbcompass.msclientes.entity.Cliente;
import com.pbcompass.msclientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteRespostaDto cadastrar(ClienteCadastrarDto dto) {
        Cliente cliente = dto.toCliente();
        Cliente clienteSalvo = clienteRepository.save(cliente);
        ClienteRespostaDto respostaDto = new ClienteRespostaDto(clienteSalvo);
        return respostaDto;
    }

    public ClienteRespostaDto buscarPorCpf(String cpf) {
        Cliente cliente =  clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new EntityNotFoundException("Cliente com o cpf " + cpf + " não encontrado.")
        );
        ClienteRespostaDto resposta = new ClienteRespostaDto(cliente);
        resposta.add(linkTo(methodOn(ClienteController.class).buscarPorCpf(cpf)).withSelfRel());
        return resposta;
    }

    @Transactional(readOnly = true)
    public List<ClienteRespostaDto> buscarTodos() {
        var clientes = clienteRepository.findAll();
        List<ClienteRespostaDto> resposta = clientes.stream()
                .map(cliente -> new ClienteRespostaDto(cliente))
                .collect(Collectors.toList());


        resposta.forEach(clienteRespostaDto -> clienteRespostaDto
                .add(linkTo(methodOn(ClienteController.class)
                        .buscarPorCpf(clienteRespostaDto.getCpf())).withSelfRel()));

        return resposta;
    }

}
