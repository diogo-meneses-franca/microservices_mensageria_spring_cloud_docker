package com.pbcompass.msclientes.controller;

import com.pbcompass.msclientes.dto.ClienteCadastrarDto;
import com.pbcompass.msclientes.entity.Cliente;
import com.pbcompass.msclientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Cliente> cadastrar(@RequestBody ClienteCadastrarDto dto){
        Cliente cliente = service.cadastrar(dto.toCliente());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(dto.getCpf()).toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @GetMapping(
            params = "cpf",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Cliente> buscarPorCpf(@RequestParam("cpf") String cpf){
        Cliente cliente = service.buscarPorCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Cliente>> buscarTodos(){
        return ResponseEntity.ok().body(service.buscarTodos());
    }
}
