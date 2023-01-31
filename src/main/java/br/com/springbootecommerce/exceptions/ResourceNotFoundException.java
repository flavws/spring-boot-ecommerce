package br.com.springbootecommerce.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg){
        super();
    } //passa o argumento para a runtime exception
}
