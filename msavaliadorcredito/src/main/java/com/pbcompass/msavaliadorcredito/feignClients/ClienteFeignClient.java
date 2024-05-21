package com.pbcompass.msavaliadorcredito.feignClients;

import com.pbcompass.msavaliadorcredito.entity.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteFeignClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<DadosCliente> buscarPorCpf(@RequestParam("cpf") String cpf);


}
