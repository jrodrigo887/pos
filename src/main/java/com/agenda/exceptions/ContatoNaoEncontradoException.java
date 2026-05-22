package com.agenda.exceptions;

public class ContatoNaoEncontradoException extends RuntimeException {
    public ContatoNaoEncontradoException(String message) {
        super(message);
    }
}
