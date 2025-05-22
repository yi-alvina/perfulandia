package com.alvina.msvc.productos.exceptions;

import com.alvina.msvc.productos.controllers.RegistroCategoriaController;

public class RegistroCategoriaException extends RuntimeException{
    public RegistroCategoriaException(String message){
        super(message);
    }
}
