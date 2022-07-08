package br.com.gft.exception;

public class LojaException extends RuntimeException{

    private String message;

    public LojaException(String message) {
        super(message);
        this.message= message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
