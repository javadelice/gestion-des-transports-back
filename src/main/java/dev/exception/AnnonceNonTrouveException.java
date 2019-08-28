package dev.exception;

public class AnnonceNonTrouveException extends RuntimeException {

	public AnnonceNonTrouveException(){
        super();
    }
    
    public AnnonceNonTrouveException(String s){
        super(s);
    }
}
