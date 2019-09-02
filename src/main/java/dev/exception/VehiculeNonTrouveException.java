package dev.exception;

public class VehiculeNonTrouveException extends RuntimeException {

	public VehiculeNonTrouveException(){
        super();
    }
    
    public VehiculeNonTrouveException(String s){
        super(s);
    }
	
}
