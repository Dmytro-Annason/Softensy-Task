package com.nekoder.Softensy.Task.exceptions;

public class ObjectIsNotExistsException extends RuntimeException {
    public ObjectIsNotExistsException(Long id){
        super("Object with id "+id+" doesn`t exists. Please, change id or don`t use id");
    }
}
