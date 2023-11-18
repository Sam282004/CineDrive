package com.example.maincinedrive.model.exceptions;

public class AlmacenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlmacenException(String mensaje) {
        super(mensaje);
    }

    public AlmacenException(String mensaje, Throwable excepetion) {
        super(mensaje, excepetion);
    }
}
