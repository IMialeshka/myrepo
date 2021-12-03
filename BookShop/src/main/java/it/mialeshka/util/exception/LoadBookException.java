package it.mialeshka.util.exception;

public class LoadBookException extends Exception{
    private String message;
    public LoadBookException(String message){
        super(message);
    }

}
