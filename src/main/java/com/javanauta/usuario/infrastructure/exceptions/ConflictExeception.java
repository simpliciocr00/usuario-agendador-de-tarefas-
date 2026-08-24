package com.javanauta.usuario.infrastructure.exceptions;

public class ConflictExeception extends RuntimeException{

    public ConflictExeception(String mensagem){
        super(mensagem);
    }

    public ConflictExeception(String mensagem, Throwable throwable){
        super(mensagem);
    }
}
