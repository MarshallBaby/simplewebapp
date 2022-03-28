package by.marshallbaby.simplewebapp.exception;

public class IdParameterMismatchException  extends RuntimeException{
    public IdParameterMismatchException(){
        super("ID parameters mismatch.");
    }
}
