package com.pbcompass.msavaliadorcredito.exception;

import lombok.Getter;

public class ErroComunicacaoMicroservicosException extends RuntimeException {

    @Getter
    private Integer status;

    public ErroComunicacaoMicroservicosException(String mensagem, Integer status) {
        super(mensagem);
        this.status = status;
    }
}
